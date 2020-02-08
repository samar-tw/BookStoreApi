package com.example.bookstore.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
@Component
public class ErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Map<String, Object> attributesToReturn = new HashMap<>();
        attributesToReturn.put("message", errorAttributes.get("message"));

        return attributesToReturn;
    }
}
