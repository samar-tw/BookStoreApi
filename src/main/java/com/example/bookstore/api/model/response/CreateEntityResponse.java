package com.example.bookstore.api.model.response;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
public class CreateEntityResponse {
    private int id;

    public CreateEntityResponse(int id) {
        this.id = id;
    }

    public CreateEntityResponse() {
    }

    public int getId() {
        return id;
    }
}
