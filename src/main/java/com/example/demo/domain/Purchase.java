package com.example.demo.domain;

import com.example.demo.domain.enums.ChattingType;
import com.example.demo.domain.enums.PurchaseType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Long price;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private PurchaseType purchaseType;

    @OneToOne
    @JoinColumn(name="CHAT_ID", nullable = true)
    private Chat chat;
}
