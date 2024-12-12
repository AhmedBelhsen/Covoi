package com.example.covoiturage.Entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table (name="User")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable=false , length=50)
    private String name;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    @Column (name="password",nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    enum Role {
        Conducteur,
        Passager
    }
    public User() {
    }
}

