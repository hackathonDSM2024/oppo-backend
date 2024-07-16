package com.example.demo.exceptions;

public class BalanceNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new BalanceNotFoundException();
    private BalanceNotFoundException() {
        super(ErrorCode.BALANCE_NOT_FOUND);
    }
}