package com.example.demo.controller.dto.response;


import com.example.demo.domain.*;
import com.example.demo.domain.enums.*;
import java.util.*;
import java.util.stream.*;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder

public class PurchaseResponse {
    List<PurchaseHistory> purchaseHistory;

    public static PurchaseResponse of(List<Purchase> purchaseList){
        return new PurchaseResponse(
          purchaseList.stream().map(PurchaseHistory::of).collect(Collectors.toList())
        );
    }
}

@Getter
@Builder

class PurchaseHistory{
    private String name;
    private Long price;
    private String description;
    private PurchaseType type;
    private Long chatId;

    public static PurchaseHistory of(Purchase purchase) {
        return PurchaseHistory.builder()
                .name(purchase.getName())
                .price(purchase.getPrice())
                .description(purchase.getDescription())
                .type(purchase.getPurchaseType())
                .chatId(purchase.getId())
                .build();
    }
}
