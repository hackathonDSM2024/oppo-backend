package com.example.demo.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ChattingType {
    SYSTEM("system"),
    CHATGPT("assistant"),
    USER("user");

    private final String name;
}
