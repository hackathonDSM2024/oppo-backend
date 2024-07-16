package com.example.demo.controller.dto.response;

import com.example.demo.domain.*;
import java.util.*;
import java.util.stream.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class ChatHistoryResponse {
    List<ChatHistory> chats;
    public static ChatHistoryResponse of(List<ChatMessage> chatMessages) {
        return new ChatHistoryResponse(
            chatMessages.stream()
                .map(ChatHistory::of).collect(Collectors.toList())
        );
    }
}

@Getter
@Builder
class ChatHistory {
    String type;
    String content;
    public static ChatHistory of(ChatMessage chatMessage) {
        return ChatHistory.builder()
            .type(chatMessage.getType().getName())
            .content(chatMessage.getContent())
            .build();
    }
}