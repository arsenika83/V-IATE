package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="groups")
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long faculty_id;
    private String name;
    private Integer year;
    private Long[] students;
    private Long[] semester_1;
    private Long[] semester_2;
    private String type;

    public Group(Long faculty_id, String name, Integer year, Long[] students, Long[] semester_1, Long[] semester_2, String type) {
        this.faculty_id = faculty_id;
        this.name = name;
        this.year = year;
        this.students = students;
        this.semester_1 = semester_1;
        this.semester_2 = semester_2;
        this.type = type;
    }

    public Group(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Group(String name) {
        this.name = name;
    }

    public Group() {}
}
