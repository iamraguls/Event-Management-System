package com.project.EventManagementSystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @OneToMany
    private List<String> roles;

    @OneToMany
    private List<Registration> registrations;
}
