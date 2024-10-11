package com.manhnguyen21.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class MessagesUtils {
    private static final ResourceBundle messageBundle = ResourceBundle.getBundle("messages.messages", Locale.getDefault());

    public static String getMessage(String errorCode, Object... args) {
        String message;
        try {
            message = messageBundle.getString(errorCode);
        } catch (Exception e) {
            message = errorCode;
        }
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, args);
        return formattingTuple.getMessage();
    }

}
