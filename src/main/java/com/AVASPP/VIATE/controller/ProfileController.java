package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.Student;
import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Long id = user.getId();
        String role = user.getRole();

        if(role.contains("STUDENT")) {
            Student profile = studentService.show(id);
            model.addAttribute("profile", profile);
        }
        else if(role.contains("TEACHER")) {

        }
        else if(role.contains("ADMIN")) {

        }

        return "profile";
    }


}
