package com.manhnguyen21.product.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class MessagesUtils {
    private static final ResourceBundle messageBundle = ResourceBundle.getBundle("messages.messages", Locale.getDefault());

    public static String getMessage(String code, Object... args) {
        String message;
        try {
            message = messageBundle.getString(code);
            message = String.format(message, args);
        } catch (Exception e) {
            log.debug(e.getMessage());
            message = code;
        }
        return MessageFormatter.arrayFormat(message, args).getMessage();
    }

    public static String getMessage(String code) {
        return getMessage(code, null);
    }
}
