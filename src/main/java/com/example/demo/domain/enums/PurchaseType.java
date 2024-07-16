package com.example.demo.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PurchaseType {
    DIRECT("DIRECT"),
    CHAT_PURCHASE("CHAT_PURCHASE"),
    CHAT_GIVE_UP("CHAT_GIVE_UP");

    private final String purchaseType;
}
