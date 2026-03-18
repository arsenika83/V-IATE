package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.TimeWithTimeZoneJdbcType;

import java.sql.Time;
import java.time.OffsetTime;

@Entity
@Table(name="news")
@Getter
@Setter

public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private String text;
    private String image_url;
    private long view_count;

    public News(String title, String description, String text) {
        this.title = title;
        this.description = description;
        this.text = text;
        this.view_count = 0;
    }

    public News(String title, String description, String text, String image_url, OffsetTime published_at) {
        this.title = title;
        this.description = description;
        this.text = text;
        this.image_url = image_url;
        this.view_count = 0;
    }
    public News() {}
}
