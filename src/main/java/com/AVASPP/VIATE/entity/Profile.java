package com.AVASPP.VIATE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@Getter
@Setter
public class Profile {
    @Id
    private Long id; //from user
    private String[] full_name; //from user
    private String birth_date;
    private String phone;
    private String email;

    @Column(name="\"group\"")
    private String group;

    private String login; //from user

    public Profile(User user, String birth_date, String phone, String email, String group) {
        id = user.getId();
        full_name = user.getFull_name();
        login = user.getLogin();

        this.birth_date = birth_date;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public Profile(User user) {
        id = user.getId();
        full_name = user.getFull_name();
        login = user.getLogin();
    }

    public Profile() {
    }

}
