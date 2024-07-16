package com.example.demo.controller;

import com.example.demo.controller.dto.request.*;
import com.example.demo.controller.dto.response.*;
import com.example.demo.domain.*;
import com.example.demo.exceptions.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {

    private final BalanceRepository balanceRepository;

    void minusBalance(Long value) {
        Balance balance = balanceRepository.findById(1L)
            .orElseThrow(() -> BalanceNotFoundException.EXCEPTION);

        balance.setBalance(balance.getBalance() - value);
        balanceRepository.save(balance);
    }
}
