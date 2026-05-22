package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id
    private Long id; //from users
    private String[] full_name; //from users
    private Long group_id; //from groups
    private String email;
    private String phone;
    private Date birth_date;
    private Long[] favorite_group_ids;

    public Student(User user, Date birth_date, String phone, String email, Long group_id) {
        id = user.getId();
        full_name = user.getFull_name();

        this.group_id = group_id;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
        this.favorite_group_ids = new Long[1];
        this.favorite_group_ids[0] = group_id;
    }

    public Student(User user) {
        id = user.getId();
        full_name = user.getFull_name();
    }

    public Student() {
    }

}
