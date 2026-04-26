package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;

@Entity
@Table(name = "task_submissions")
@Getter
@Setter

public class TaskSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long task_id;
    private Long user_id;
    private String file_name;
    private String file_path;
    private LocalTime submitted_at;
    private String status;
    private String comment;

    public TaskSubmission() {}
    public TaskSubmission(Long task_id, Long user_id, String file_name, String file_path) {
        this.task_id = task_id;
        this.user_id = user_id;
        this.file_name = file_name;
        this.file_path = file_path;
        this.submitted_at = LocalTime.now();
        this.status = "REVIEW";
    }
}
