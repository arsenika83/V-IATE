package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.*;
import com.AVASPP.VIATE.service.CourseService;
import com.AVASPP.VIATE.service.GroupService;
import com.AVASPP.VIATE.service.StudentService;
import com.AVASPP.VIATE.service.TaskService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/course_lobby")
    public String courses(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if(user != null) {
            Long user_id = user.getId();
            String role = user.getRole();
            model.addAttribute("role", role);

            if(role.contains("STUDENT")) {
                Student profile = studentService.findOrSaveStudent(user_id);
                Group group = groupService.findById(profile.getGroup_id());
                List<Course> student_courses = courseService.getAllCoursesByGroupId(group.getId());

                model.addAttribute("profile", profile);
                model.addAttribute("group", group);
                model.addAttribute("student_courses", student_courses);
            }
            else if(role.contains("TEACHER")) {

            }
            else if(role.contains("ADMIN")) {

            }
            return "course_lobby";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/course")
    public String course(HttpSession session, Model model, @RequestParam Long id) {
        User user = (User) session.getAttribute("user");

        if(user != null) {
            Long user_id = user.getId();
            String role = user.getRole();
            model.addAttribute("role", role);

            if(role.contains("STUDENT")) {
                Course course = courseService.findById(id);
                Student student = studentService.findOrSaveStudent(user_id);

                if(student.getGroup_id().longValue() != course.getGroup_id().longValue()) {
                    return "redirect:/course_lobby";
                }

                List<Task> tasks = taskService.findAllByCourseId(course.getId());
                model.addAttribute("tasks", tasks);
                model.addAttribute("profile", student);
                model.addAttribute("course", course);
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

    @PutMapping("/update_task_status/{id}")
    public String update_task_status(HttpSession session, Model model, @PathVariable Long id, @RequestParam String status) {

        taskService.updateStatus(id, status);

        return "update_task_status";
    }
}
