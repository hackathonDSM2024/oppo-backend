package com.example.demo.domain;

import com.example.demo.domain.enums.ChattingType;
import jakarta.persistence.*;
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
    private ChattingType type;

    @Column(name = "CONTENT", nullable = true)
    private String content;

    @ManyToOne(targetEntity = Chat.class, fetch = FetchType.LAZY)
    @JoinColumn(name="CHAT_ID")
    private Chat chat;

}
