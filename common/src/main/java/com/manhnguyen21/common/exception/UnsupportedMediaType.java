package com.manhnguyen21.common.exception;

import com.manhnguyen21.common.utils.MessagesUtils;

public class UnsupportedMediaType extends RuntimeException{
    private final String message;

    public UnsupportedMediaType(String message, Object... args) {
        this.message = MessagesUtils.getMessage(message, args);
    }
}
