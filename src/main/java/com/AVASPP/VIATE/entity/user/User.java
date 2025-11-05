package com.AVASPP.VIATE.entity.user;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class User {
    private String name = "Leha";

    @Bean
    public User getUser() {
        return new User();
    }
}