package com.example.bookstore.exceptions;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
