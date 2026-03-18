package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.entity.Student;
import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.service.GroupService;
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

    @Autowired
    private GroupService groupService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Long id = user.getId();
        String role = user.getRole();

        if(role.contains("STUDENT")) {
            Student profile = studentService.show(id);
            Group group = groupService.findById(profile.getGroup_id());
            model.addAttribute("profile", profile);
            model.addAttribute("group", group);
        }
        else if(role.contains("TEACHER")) {

        }
        else if(role.contains("ADMIN")) {

        }

        return "profile";
    }

    @GetMapping("/error")
    public String error(HttpSession session, Model model) {
        return "redirect:/login";
    }

}
