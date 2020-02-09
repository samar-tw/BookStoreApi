package com.example.bookstore.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Ehtiram_Abdullayev on 2/9/2020
 * @project book-store
 */
public class TestUtils {
    public static LocalDate convertStringToDate(String dateStr) {
        return LocalDate.parse(dateStr);
    }

    public static ObjectMapper createObjectMapperInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;
    }

    public static LocalDateTime truncateToSeconds(LocalDateTime localDateTime) {
        return localDateTime.truncatedTo(ChronoUnit.SECONDS);
    }
}
