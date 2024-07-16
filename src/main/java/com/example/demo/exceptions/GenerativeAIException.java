package com.example.demo.exceptions;

public class GenerativeAIException extends BusinessException {
    public static final BusinessException EXCEPTION = new GenerativeAIException();
    private GenerativeAIException() {
        super(ErrorCode.GENERATIVE_AI_ERROR);
    }
}