package com.example.complaint_system.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "CPT_STM_USER")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Maps to SERIAL in PostgreSQL
    private Long id;

    @Column(nullable = false , unique = true , length = 100)
    private String email;

    @Column(nullable = false , length = 100)
    private String username;

    @Column(nullable = false , length = 200)
    private String password;

    @Column(length = 50)
    private String role;

//    Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
