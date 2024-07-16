package com.example.demo.controller.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
public class CreateChatRequest {
    String name;
    Long price;
    String description;
}
