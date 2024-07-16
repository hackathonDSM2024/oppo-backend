package com.example.demo.exceptions;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // 유저
    BAD_USER_INFORMATION(404, "Bad User Information"),
    FORBIDDEN_USER(403, "Forbidden User"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    USER_NOT_FOUND(404, "User Not Found"),
    PASSWORD_MISMATCH(401, "Password Mismatch"),

    // 토큰
    EXPIRED_TOKEN(401 , "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token"),

    BALANCE_NOT_FOUND(404 , "Balance Not Found"),
    CHAT_NOT_FOUND(404 , "Chat Not Found"),

    // 서버에러
    GENERATIVE_AI_ERROR(500, "GENERATIVE_AI_ERROR"),
    INTERNAL_SERVER_ERROR(500,"Server Error");

    private final Integer statusCode;
    private final String message;
}