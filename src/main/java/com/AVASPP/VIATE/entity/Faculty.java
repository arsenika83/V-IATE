package com.AVASPP.VIATE.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="faculties")
@Getter
@Setter
public class Faculty {
    @Id
    private long id;
    private String name;
    private String name_short;

    public Faculty(String name, String name_short) {
        this.name = name;
        this.name_short = name_short;
    }

    public Faculty() {}
}
