package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/user/name")
    public User postUser(@PathVariable String name) {
        return userService.save(new User(name, name, new String[] {name}, "STUDENT"));
    }
}
