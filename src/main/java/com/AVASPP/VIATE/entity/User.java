package com.AVASPP.VIATE.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter

public class User {
    private Long id;
    private String name;

}