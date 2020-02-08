package com.example.bookstore.api.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class BasicRequest {

    @NotNull(message = "{id.notNull}")
    @Positive(message = "{id.positive}")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public BasicRequest() {
    }

    public BasicRequest(Integer id) {
        this.id = id;
    }
}
