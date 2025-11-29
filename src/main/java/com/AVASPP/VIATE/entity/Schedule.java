package com.AVASPP.VIATE.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="schedule")
@Getter
@Setter
public class Schedule {
    @Id
    private long group_id;
    private long[] monday;
    private long[] tuesday;
    private long[] wednesday;
    private long[] thursday;
    private long[] friday;
    private long[] saturday;

    public Schedule(long group_id, long[] monday, long[] tuesday, long[] wednesday, long[] thursday, long[] friday, long[] saturday) {
        this.group_id = group_id;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }

    public Schedule(long group_id) {
        this.group_id = group_id;
    }

    public Schedule() {}
}
