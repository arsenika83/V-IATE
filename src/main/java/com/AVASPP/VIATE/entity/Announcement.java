package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "announcements")
@Getter
@Setter

@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String text;
    private String link;

    public Announcement(String title, String text, String link) {
        this.title = title;
        this.text = text;
        this.link = link;
    }
    public Announcement() {}
}
