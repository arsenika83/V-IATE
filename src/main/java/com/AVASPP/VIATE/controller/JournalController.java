package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.entity.Journal;
import com.AVASPP.VIATE.entity.Student;
import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JournalController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private JournalService journalService;

    @Autowired
    private StudentStatisticService studentStatisticService;

    @Autowired
    private UserService userService;

    @GetMapping("/journal_lobby")
    public String journal_lobby(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if(user != null) {
            Long id = user.getId();
            String role = user.getRole();

            if(role.contains("STUDENT")) {
                Student student = studentService.findOrSaveStudent(id);
                Group group = groupService.findById(student.getGroup_id());

                List<Journal> journals = journalService.findByGroupId(group.getId());
                int[] journal_numbers = new int[journals.size()];

                List<String> teacher_names = new ArrayList<>();
                for (int i = 0; i < journals.size(); i++) {
                    journal_numbers[i] = i;
                    String[] fullname = userService.findById(journals.get(i).getTeacher_id()).getFull_name();

                    teacher_names.add(fullname[0] + " " + fullname[1].charAt(0)+ ". " + fullname[2].charAt(0) + ".");
                }


                model.addAttribute("profile", student);
                model.addAttribute("group", group);
                model.addAttribute("journals", journals);
                model.addAttribute("journal_numbers", journal_numbers);
                model.addAttribute("teacher_names", teacher_names);
            }
            else if(role.contains("TEACHER")) {

            }
            else if(role.contains("ADMIN")) {

            }
            return "journal_lobby";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/journal/{id}")
    public String journal(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if(user != null) {
            Long id = user.getId();
            String role = user.getRole();

            if(role.contains("STUDENT")) {
                Student student = studentService.findOrSaveStudent(id);
                Group group = groupService.findById(student.getGroup_id());

                List<Journal> journals = journalService.findByGroupId(group.getId());

                String teacher_name = "Перетятько Л.О.";

                model.addAttribute("profile", student);
                model.addAttribute("group", group);
                model.addAttribute("journals", journals);
                model.addAttribute("teacher_name", teacher_name);
            }
            else if(role.contains("TEACHER")) {

            }
            else if(role.contains("ADMIN")) {

            }
            return "journal_lobby";
        }
        else {
            return "redirect:/login";
        }
    }
}
