package com.example.ms.card.exception;

import lombok.Getter;

@Getter
public class MethodNotAllowedException extends RuntimeException {
    private final String code;

    public MethodNotAllowedException(String code, String message) {
        super(message);
        this.code = code;
    }
}
