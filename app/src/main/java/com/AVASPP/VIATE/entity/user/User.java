package main.java.com.AVASPP.VIATE.entity.user;

import org.springframework.context.annotation.Bean;

public class User {
    private String name = "Leha";

    @Bean
    public String getName() {
        return name;
    }
}