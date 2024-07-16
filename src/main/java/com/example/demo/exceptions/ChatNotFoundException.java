package com.example.demo.exceptions;

public class ChatNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new ChatNotFoundException();
    private ChatNotFoundException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}