package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="lessons")
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long group_id;
    private long teacher_id;
    private String teacher_name;
    private String time;
    private String classroom;
    private String week;
    private String day;
    private String name;
    private String type;

    public Lesson(long group_id, long teacher_id, String teacher_name, String time, String classroom, String week, String day, String name, String type) {
        this.group_id = group_id;
        this.teacher_id = teacher_id;
        this.time = time;
        this.classroom = classroom;
        this.week = week;
        this.day = day;
        this.name = name;
        this.type = type;
    }
    public Lesson() {}

}
