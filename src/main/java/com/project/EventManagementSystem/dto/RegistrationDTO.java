package com.project.EventManagementSystem.dto;

import com.project.EventManagementSystem.model.Registration;

import java.time.LocalDateTime;

public class RegistrationDTO {

    private Long id;
    private LocalDateTime registeredAt;
    private String email;
    private String eventname;

    // 🔥 Constructor to convert Entity → DTO
    public RegistrationDTO(Registration registration) {
        this.id = registration.getId();
        this.registeredAt = registration.getRegisteredAt();
        this.email = registration.getUsers().getEmail();
        this.eventname = registration.getEvents().getName();
    }

    // 🔥 Empty Constructor (Needed for Jackson Serialization)
    public RegistrationDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }
}
