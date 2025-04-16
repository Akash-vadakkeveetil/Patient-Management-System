package com.patient_service.patient_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;//id will get automatically handled by spring jpa

    @NotNull
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String address;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private LocalDate registeredDate;


    public Patient() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public @NotNull String getAddress() {
        return address;
    }

    public Patient(UUID id, @NotNull String name, @NotNull String email, @NotNull String address, @NotNull LocalDate dateOfBirth, @NotNull LocalDate registeredDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.registeredDate = registeredDate;
    }

    public void setAddress(@NotNull String address) {
        this.address = address;
    }

    public @NotNull LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotNull LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotNull LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(@NotNull LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}

