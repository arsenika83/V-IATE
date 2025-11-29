package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id
    private Long id; //from users
    private String[] full_name; //from users
    private String birth_date;
    private String phone;
    private String email;
    private String group_name; //from groups
    private String login; //from users

    public Student(User user, String birth_date, String phone, String email, String group_name) {
        id = user.getId();
        full_name = user.getFull_name();
        login = user.getLogin();

        this.birth_date = birth_date;
        this.phone = phone;
        this.email = email;
        this.group_name = group_name;
    }

    public Student(User user) {
        id = user.getId();
        full_name = user.getFull_name();
        login = user.getLogin();
    }

    public Student() {
    }

}
