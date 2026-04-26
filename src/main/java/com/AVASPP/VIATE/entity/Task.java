package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalTime deadline;
    private Long course_id;
    private Long[] files;
    private boolean is_open;
    private Long[] submission_id;

    public Task() {}
    public Task(String title, Long course_id) {
        this.title = title;
        this.course_id = course_id;
        this.is_open = true;
    }
}
