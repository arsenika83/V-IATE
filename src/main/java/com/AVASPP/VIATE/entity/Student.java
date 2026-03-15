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
    private String login; //from users
    private String email;
    private String phone;
    private Date birth_date;

    public Student(User user, Date birth_date, String phone, String email, Long group_id) {
        id = user.getId();
        full_name = user.getFull_name();
        login = user.getLogin();

        this.group_id = group_id;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
    }

    public Student(User user) {
        id = user.getId();
        full_name = user.getFull_name();
        login = user.getLogin();
    }

    public Student() {
    }

}
