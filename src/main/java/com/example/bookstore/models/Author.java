package com.example.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ehtiram_Abdullayev on 2/4/2020
 * @project book-store
 */

@Entity
@DynamicUpdate
@Table(name = "authors")
public class Author extends BasicEntity {

    @NotBlank
    @Column
    private String firstName;

    @NotBlank
    @Column
    private String lastName;

    @Column
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "bookAuthors", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("bookAuthors")
    private Set<Book> authorBooks;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Book> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(Set<Book> authorBooks) {
        this.authorBooks = authorBooks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), firstName, lastName, birthDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Author)) {
            return false;
        }

        Author other = (Author) obj;

        return Objects.equals(this.getId(), other.getId())
                && Objects.equals(firstName, other.firstName)
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(birthDate, other.birthDate);
    }
}