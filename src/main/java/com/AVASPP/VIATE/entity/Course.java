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

    private String title;
    private Long group_id;
    private Long[] tasks;
    private Long[] files;
    private Long teacher_id;
    private String teacher_name;

    public Course() {}
    public Course(String name, Long group_id, Long teacher_id) {
        this.title = name;
        this.group_id = group_id;
        this.teacher_id = teacher_id;
    }
    public Course(String name, Long group_id, Long[] tasks, Long[] files, Long teacher_id) {
        this.title = name;
        this.group_id = group_id;
        this.tasks = tasks;
        this.files = files;
        this.teacher_id = teacher_id;
    }
}
