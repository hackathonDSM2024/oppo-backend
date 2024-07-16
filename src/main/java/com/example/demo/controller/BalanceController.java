package com.example.demo.controller;

import com.example.demo.controller.dto.request.*;
import com.example.demo.controller.dto.response.*;
import com.example.demo.domain.*;
import com.example.demo.exceptions.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceRepository balanceRepository;

    @GetMapping
    BalanceResponse getBalance() {
        Balance balance = balanceRepository.findById(1L)
            .orElseThrow(() -> BalanceNotFoundException.EXCEPTION);
        return BalanceResponse.builder()
            .balance(balance.getBalance())
            .build();
    }

    @PatchMapping
    BalanceResponse modifyBalance(@RequestBody BalanceRequest request) {
        Balance balance = balanceRepository.findById(1L)
            .orElseThrow(() -> BalanceNotFoundException.EXCEPTION);

        balance.setBalance(request.getBalance());
        balanceRepository.save(balance);

        return BalanceResponse.builder()
            .balance(balance.getBalance())
            .build();
    }
}
