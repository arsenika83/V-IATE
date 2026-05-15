package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="journals")
@Getter
@Setter
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private long[] student_statistics;
    private long teacher_id;
    private String type;
    private int hours_total = 0;
    private int hours_current = 0;
    private long group_id;

    public Journal() {}

    public Journal(String title, long teacher_id, String type, long group_id, int hours_total, int hours_current) {
        this.title = title;
        this.teacher_id = teacher_id;
        this.type = type;
        this.group_id = group_id;
        this.hours_total = hours_total;
        this.hours_current = hours_current;
    }

}
