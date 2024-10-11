package com.manhnguyen21.common.exception;

import com.manhnguyen21.common.utils.MessagesUtils;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private final String message;

    public NotFoundException(String errorCode, Object... args) {
        this.message = MessagesUtils.getMessage(errorCode, args);
    }
}
