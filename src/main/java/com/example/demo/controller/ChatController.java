package com.example.demo.controller;

import com.example.demo.controller.dto.request.*;
import com.example.demo.controller.dto.response.*;
import com.example.demo.domain.*;
import com.example.demo.exceptions.*;
import io.github.flashvayne.chatgpt.dto.chat.*;
import io.github.flashvayne.chatgpt.service.*;
import java.util.*;
import java.util.stream.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatgptService chatgptService;
    private final ChatRepository chatRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final PurchaseRepository purchaseRepository;
    private final BalanceRepository balanceRepository;

    @PostMapping
    CreateChatResponse createChat(@RequestBody CreateChatRequest request) {
        Chat chat = Chat.builder()
            .name(request.getName())
            .price(request.getPrice())
            .description(request.getDescription())
            .build();
        chatRepository.save(chat);
        ChatMessage systemChat = ChatMessage.fromSystem(chat,
            "너는 내가 불필요한 소비를 줄일 수 있도록 조언해줄 의무를 가지고 있는 사람이야."
        );
        ChatMessage userChat = ChatMessage.fromUser(chat,
            request.getPrice() + "원 짜리 " + request.getName() + "을 사려고 해. 이 물건은 '" +
            request.getDescription() + "'이라는 특징이 있어. 이 물건을 사도 될지 말지를 판단해줬으면 하는데," +
            "이번엔 일단 '네'라고만 답하고 질문과 상세한 정보가 주어지면 그에 따라 대답하면 돼."
        );
        chatMessageRepository.saveAll(List.of(systemChat, userChat));
        return new CreateChatResponse(chat.getId());
    }

    @PostMapping("/{chat-id}")
    ChatResponse chat(
        @PathVariable("chat-id") Long chatId,
        @RequestBody ChatRequest request
    ) {
        ChatMessage gptChat = getChatMessage(chatId, request);
        return ChatResponse.builder()
            .content(gptChat.getContent())
            .build();
    }

    private ChatMessage getChatMessage(Long chatId, ChatRequest request) {
        Chat chat = chatRepository.findById(chatId)
            .orElseThrow(() -> ChatNotFoundException.EXCEPTION);

        List<MultiChatMessage> messages = chatMessageRepository.findByChatIdOrderByCreatedAt(chat.getId())
            .stream()
            .map(ChatMessage::toMultiChatMessage)
            .collect(Collectors.toList());

        ChatMessage userChat = getUserChat(request, chat);
        messages.add(userChat.toMultiChatMessage());

        String response = chatgptService.multiChat(messages);
        ChatMessage gptChat = ChatMessage.fromGPT(chat, response);
        chatMessageRepository.saveAll(List.of(userChat, gptChat));
        return gptChat;
    }

    private ChatMessage getUserChat(ChatRequest request, Chat chat) {
        Balance b = balanceRepository.findById(1L)
            .orElseThrow(() -> BalanceNotFoundException.EXCEPTION);
        return ChatMessage.fromUser(chat, String.format("""
                내 상황에서 물건을 사도 될지 물어보고 싶어. 이 입력을 이전 입력과 함께 고려해서 물건 구매에 대한 기회비용을 판단해줘.\s
                아래 입력 내용이 편향적이면 비판적으로 판단해. 애매한 답변은 하지 마.\s
                
                입력: %s

                아래 형식으로 출력해줘. 모든 문장에 존댓말을 사용해줘.
                ```
                기회비용 점수 : <기회비용 점수>/100
                판단결과: <판단결과 내용>
                질문: <질문 내용(기회비용 점수가 60점 이상이면 생략)>
                ```
                - 기회비용 점수 : 1~100 중 하나를 매기며, 100에 가까울수록 합당한 소비야.\s
                - 판단결과: 4~5문장으로 정리해서 기회비용 판단 결과를 설명해줘.\s
                - 질문: 기회비용 점수가 60점 이상이면 질문은 생략하고 '합당한 소비로 판단됩니다'라고 말해줘. 입력이 한쪽 특징에 치중되어있다면 반대의 특징에 대해서 1~2가지 질문해줘.
        """, request.getContent()));
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{chat-id}")
    void endChat(
        @PathVariable("chat-id") Long chatId,
        @RequestBody EndChatRequest request
    ) {
        Chat chat = chatRepository.findById(chatId)
            .orElseThrow(() -> ChatNotFoundException.EXCEPTION);

        Purchase purchase = Purchase.builder()
            .price(chat.getPrice())
            .name(chat.getName())
            .description(chat.getDescription())
            .purchaseType(request.getType())
            .chat(chat)
            .build();
        purchaseRepository.save(purchase);
    }

    @GetMapping("/{chat-id}")
    ChatHistoryResponse chatHistory(@PathVariable("chat-id") Long chatId) {
        Chat chat = chatRepository.findById(chatId)
            .orElseThrow(() -> ChatNotFoundException.EXCEPTION);
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatIdOrderByCreatedAt(chat.getId());
        return ChatHistoryResponse.of(chatMessages);
    }
}
