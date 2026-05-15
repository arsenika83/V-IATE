package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student_statistics")
@Getter
@Setter
public class StudentStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long student_id;
    private long journal_id;
    private int missed_classes;
    private int mark;

    public StudentStatistic(long student_id, long journal_id) {
        this.student_id = student_id;
        this.journal_id = journal_id;
    }
}
