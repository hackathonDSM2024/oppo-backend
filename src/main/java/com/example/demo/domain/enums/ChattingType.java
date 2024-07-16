package com.example.demo.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ChattingType {
    CHATGPT("CHATGPT"),
    USER("USER");

    private final String chattingType;
}
