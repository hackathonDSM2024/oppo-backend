package com.example.demo.controller.dto.response;


import com.example.demo.domain.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

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

    public static PurchaseHistory of(Purchase purchase) {
        return PurchaseHistory.builder()
                .name(purchase.getName())
                .price(purchase.getPrice())
                .description(purchase.getDescription())
                .build();
    }
}
