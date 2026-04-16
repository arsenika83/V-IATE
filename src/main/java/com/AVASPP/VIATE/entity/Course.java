package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "courses")
@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int total_hours;
    private String teacher_name;
    private Long group_id;
    private String[] files;

    public Course() {}
    public Course(String name, Long group_id) {
        this.name = name;
        this.group_id = group_id;
    }
}
