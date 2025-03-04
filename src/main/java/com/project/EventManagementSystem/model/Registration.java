package com.project.EventManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime registeredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Registration() {
    }

    public Registration(Long id, LocalDateTime registeredAt, Users user, Event event) {
        this.id = id;
        this.registeredAt = registeredAt;
        this.user = user;
        this.event = event;
    }

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

    public Users getUsers() {
        return user;
    }

    public void setUsers(Users users) {
        this.user = users;
    }

    public Event getEvents() {
        return event;
    }

    public void setEvents(Event events) {
        this.event = events;
    }
}
