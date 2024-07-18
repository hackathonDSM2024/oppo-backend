package com.example.demo.controller;

import com.example.demo.controller.dto.request.*;
import com.example.demo.controller.dto.response.*;
import com.example.demo.domain.*;
import com.example.demo.domain.enums.PurchaseType;
import com.example.demo.exceptions.*;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;
    private final BalanceRepository balanceRepository;

    @GetMapping
    PurchaseResponse getPurchase(){
        List<Purchase> purchaseList = purchaseRepository.findByOrderByIdDesc();
        return PurchaseResponse.of(purchaseList);
    }

    @PostMapping
    void createPurchase(@RequestBody PurchaseRequest purchaseRequest){
        Purchase purchase = Purchase.builder()
                .name(purchaseRequest.getName())
                .price(purchaseRequest.getPrice())
                .description(purchaseRequest.getDescription())
                .purchaseType(PurchaseType.DIRECT)
                .build();
        purchaseRepository.save(purchase);
        minusBalance(purchaseRequest.getPrice());
    }

    void minusBalance(Long value) {
        Balance balance = balanceRepository.findById(1L)
            .orElseThrow(() -> BalanceNotFoundException.EXCEPTION);

        balance.setBalance(balance.getBalance() - value);
        balanceRepository.save(balance);
    }
}
