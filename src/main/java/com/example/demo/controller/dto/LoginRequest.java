package com.example.demo.controller.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {
    private String accountId;
    private String password;
}