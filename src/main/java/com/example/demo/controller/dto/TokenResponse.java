package com.example.demo.controller.dto;

import lombok.*;

@Builder
public record TokenResponse(
    String accessToken
) {
}