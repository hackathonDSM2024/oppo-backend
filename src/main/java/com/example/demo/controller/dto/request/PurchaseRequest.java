package com.example.demo.controller.dto.request;

import com.example.demo.domain.Chat;
import com.example.demo.domain.enums.PurchaseType;
import lombok.Getter;

@Getter
public class PurchaseRequest {
    private String name;
    private Long price;
    private String description;
}
