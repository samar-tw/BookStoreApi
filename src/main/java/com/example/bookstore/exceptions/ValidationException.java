package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class ValidationException extends RuntimeException implements ICustomException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ValidationException() {
        super();
    }

    public ValidationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ValidationException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return status;
    }

}
