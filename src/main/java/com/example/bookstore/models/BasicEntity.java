package com.example.bookstore.models;

import javax.persistence.*;

/**
 * @author Ehtiram_Abdullayev on 2/4/2020
 * @project book-store
 */

@MappedSuperclass
public class BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
