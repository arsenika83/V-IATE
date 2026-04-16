package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.entity.Student;
import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.service.CourseService;
import com.AVASPP.VIATE.service.GroupService;
import com.AVASPP.VIATE.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/course")
    public String courses(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if(user != null) {
            Long id = user.getId();
            String role = user.getRole();
            model.addAttribute("role", role);

            if(role.contains("STUDENT")) {
                Student profile = studentService.findOrSaveStudent(id);
                Group group = groupService.findById(profile.getGroup_id());
                model.addAttribute("profile", profile);
                model.addAttribute("group", group);
            }
            else if(role.contains("TEACHER")) {

            }
            else if(role.contains("ADMIN")) {

            }
            return "course";
        }
        else {
            return "redirect:/login";
        }
    }
}
