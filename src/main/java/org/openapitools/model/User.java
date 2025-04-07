package org.openapitools.model;

import lombok.EqualsAndHashCode;
import org.openapitools.enums.Rol;
import org.openapitools.enums.UserStatus;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class User {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    private String fullName;
    @Past
    private LocalDate dateBirth;
    private Rol rol;
    private UserStatus status;

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}