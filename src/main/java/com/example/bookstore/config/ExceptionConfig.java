package com.example.bookstore.config;

import com.example.bookstore.api.model.response.GenericResponse;
import com.example.bookstore.exceptions.ICustomException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.exceptions.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */

@ControllerAdvice
public class ExceptionConfig {

    private final MessageProvider messageProvider;

    public ExceptionConfig(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleNotFoundException(NotFoundException ex) {
        HttpStatus status = ex.getStatus() == null ? HttpStatus.NOT_FOUND : ex.getStatus();
        return buildResponse(ex.getMessage(), status);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleValidationException(ValidationException ex) {
        HttpStatus status = ex.getStatus() == null ? HttpStatus.BAD_REQUEST : ex.getStatus();
        return buildResponse(ex.getMessage(), status);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleRuntimeException(RuntimeException exp) {
        if (exp instanceof ICustomException) {
            return buildResponse(exp.getMessage(), ((ICustomException) exp).getStatus());
        }

        String message = messageProvider.getMessage("error.internalServerError");
        return buildResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleDataIntegrityViolationException(Exception ex) {
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            String message = messageProvider.getMessage("item.unableToDelete");
            return buildResponse(message, HttpStatus.BAD_REQUEST);
        }

        return handleGlobalException();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String errorMessage = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public final ResponseEntity<GenericResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        return buildResponse(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleGlobalException() {
        String message = messageProvider.getMessage("error.internalServerError");
        return buildResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            InvalidDataAccessApiUsageException.class
    })
    @ResponseBody
    public ResponseEntity<GenericResponse> handleArgumentException(Exception ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String message = messageProvider.getMessage("json.invalidFormat");
        return buildResponse(message, HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<GenericResponse> buildResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(new GenericResponse(message), status);
    }
}
