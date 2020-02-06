package com.example.bookstore.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
@Entity
@Table(name = "users")
public class User extends BasicEntity {

    @NotBlank
    @Column
    private String firstName;

    @NotBlank
    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Email
    @NotBlank
    @Column
    private String email;

    @Column
    private LocalDate birthDate;

    @Column
    private Boolean enabled;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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


    @Override
    public String toString() {
        return "User [id=" + this.getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
                + ", email=" + email + ", birthDate=" + birthDate + ", enabled=" + enabled
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate, email, enabled, firstName, lastName, phoneNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;

        return Objects.equals(birthDate, other.birthDate) && Objects.equals(email, other.email)
                && Objects.equals(enabled, other.enabled) && Objects.equals(firstName, other.firstName)
                && Objects.equals(lastName, other.lastName) && Objects.equals(phoneNumber, other.phoneNumber);
    }
}