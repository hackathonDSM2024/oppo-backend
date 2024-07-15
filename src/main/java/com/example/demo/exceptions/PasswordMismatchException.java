package com.example.demo.exceptions;

public class PasswordMismatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new PasswordMismatchException();
    private PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}