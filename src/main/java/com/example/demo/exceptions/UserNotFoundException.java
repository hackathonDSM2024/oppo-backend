package com.example.demo.exceptions;

public class UserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotFoundException();
    private UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}