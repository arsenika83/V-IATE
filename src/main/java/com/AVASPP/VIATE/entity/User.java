package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Entity
@Table (name = "\"users\"")
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String login;
    private String password;
    private String[] full_name;

    public User(String login, String password, String[] full_name) {
        this.login = login;
        this.password = password;
        this.full_name = full_name;
    }
    public User() {}
}