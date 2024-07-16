package com.example.demo.domain;

import com.example.demo.domain.enums.*;
import io.github.flashvayne.chatgpt.dto.chat.*;
import jakarta.persistence.*;
import java.time.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private ChattingType type;

    @Column(name = "NAME", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CHAT_ID")
    private Chat chat;

    public MultiChatMessage toMultiChatMessage() {
        return new MultiChatMessage(getType().getName(), getContent());
    }

    public static ChatMessage fromUser(Chat chat, String content) {
        return getBuild(chat, ChattingType.USER, content);
    }

    public static ChatMessage fromGPT(Chat chat, String content) {
        return getBuild(chat, ChattingType.CHATGPT, content);
    }

    public static ChatMessage fromSystem(Chat chat, String content) {
        return getBuild(chat, ChattingType.SYSTEM, content);
    }

    private static ChatMessage getBuild(Chat chat, ChattingType type, String content) {
        return ChatMessage.builder()
            .type(type)
            .content(content)
            .createdAt(LocalDateTime.now())
            .chat(chat)
            .build();
    }
}
