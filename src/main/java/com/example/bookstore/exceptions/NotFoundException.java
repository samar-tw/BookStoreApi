package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
public class NotFoundException extends RuntimeException implements ICustomException{

    private HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super();
    }
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
