package com.example.demo.exceptions;


public class InvalidTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidTokenException();
    private InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN);
    }
}