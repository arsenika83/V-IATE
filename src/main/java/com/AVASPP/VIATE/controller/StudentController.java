package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.Student;
import com.AVASPP.VIATE.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable Long id) {
        Student profile = studentService.save(id);

        model.addAttribute("profile", profile);

        return "profile";
    }


}
