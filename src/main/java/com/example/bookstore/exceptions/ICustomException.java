package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public interface ICustomException {
    HttpStatus getStatus();
}

