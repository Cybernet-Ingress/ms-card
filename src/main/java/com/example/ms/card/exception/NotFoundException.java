package com.example.ms.card.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String code;

    public NotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }
}
