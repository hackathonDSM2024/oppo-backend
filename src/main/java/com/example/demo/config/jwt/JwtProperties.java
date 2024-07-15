package com.example.demo.config.jwt;

import org.springframework.boot.context.properties.*;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
    String header,
    String prefix,
    String secret,
    Long accessExpiration
) {
}