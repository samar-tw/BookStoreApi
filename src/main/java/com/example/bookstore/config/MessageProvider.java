package com.example.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
@Component
public class MessageProvider {
    private final MessageSource messageSource;

    @Autowired
    public MessageProvider(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String messageCode) {
        return getMessage(messageCode, null);
    }

    public String getMessage(String messageCode, List<Object> args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageCode, args != null ? args.toArray() : null, locale);
    }
}
