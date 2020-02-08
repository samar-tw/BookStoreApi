package com.example.bookstore.api.model.response;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class GenericResponse {

    private String message;

    public GenericResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
