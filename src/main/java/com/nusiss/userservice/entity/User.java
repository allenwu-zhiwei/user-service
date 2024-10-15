package com.nusiss.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "User")
@Setter
@Getter
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createDatetime;

    @Column(nullable = false)
    private LocalDateTime updateDatetime;

    @Column(nullable = false)
    private String createUser;

    @Column(nullable = false)
    private String updateUser;

    @PrePersist
    protected void onCreate() {
        this.createDatetime = LocalDateTime.now();
        this.updateDatetime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDatetime = LocalDateTime.now();
    }

    // Getters and Setters

    public enum Role {
        CUSTOMER, SELLER, ADMIN
    }

    // Add constructors, getters, and setters...
}