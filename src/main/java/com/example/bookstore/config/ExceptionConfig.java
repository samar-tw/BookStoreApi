package com.example.bookstore.config;

import com.example.bookstore.api.model.response.GenericResponse;
import com.example.bookstore.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleNotFoundException(NotFoundException ex) {
        HttpStatus status = ex.getStatus() == null ? HttpStatus.NOT_FOUND : ex.getStatus();
        return buildResponse(ex.getMessage(), status);
    }

    private ResponseEntity<GenericResponse> buildResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(new GenericResponse(message), status);
    }
}
