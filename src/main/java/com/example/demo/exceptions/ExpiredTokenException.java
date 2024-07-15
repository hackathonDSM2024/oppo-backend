package com.example.demo.exceptions;

public class ExpiredTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ExpiredTokenException();
    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}