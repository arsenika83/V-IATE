package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.*;
import com.AVASPP.VIATE.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentStatisticService studentStatisticService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if(user != null) {
            model.addAttribute("user_name", user.getFull_name()[1]);
            model.addAttribute("login", user.getLogin());

            if(user.getRole().equals("STUDENT")) {

                Student student = studentService.findOrSaveStudent(user.getId());
                int[] grades = studentStatisticService.estimateGradesByStudentId(student.getId());

                Long[] favorite_group_ids = student.getFavorite_group_ids();
                ArrayList<Group> favorite_groups = new ArrayList<>();
                for (int i = 0; i < favorite_group_ids.length; i++) {
                    favorite_groups.add(groupService.findById(favorite_group_ids[i]));
                }

                Schedule schedule = scheduleService.save(student.getGroup_id());
                long[] today_lessons;

                int dayOfWeek = scheduleService.getDayOfWeek();
                switch(dayOfWeek) {
                    case 1:
                        today_lessons = schedule.getMonday();
                        break;
                    case 2:
                        today_lessons = schedule.getTuesday();
                        break;
                    case 3:
                        today_lessons = schedule.getWednesday();
                        break;
                    case 4:
                        today_lessons = schedule.getThursday();
                        break;
                    case 5:
                        today_lessons = schedule.getFriday();
                        break;
                    case 6:
                        today_lessons = schedule.getSaturday();
                        break;
                    default:
                        today_lessons = schedule.getMonday();
                        break;
                }

                Lesson[] today_lessons_add = new Lesson[today_lessons.length];
                for (int id = 0; id < today_lessons.length; id++) {
                    Lesson lesson = scheduleService.findLessonById(today_lessons[id]);
                    today_lessons_add[id] = lesson;
                }
                model.addAttribute("today_lessons", today_lessons_add);

                model.addAttribute("favorite_groups", favorite_groups);
                model.addAttribute("group_id", student.getGroup_id());
                model.addAttribute("group_name", groupService.getGroupNameById(student.getGroup_id()));
                model.addAttribute("grades", grades);
            }
        }

        model.addAttribute("news", newsService.getLastArticles(10));
        model.addAttribute("announcements", announcementService.getLastAnnouncements(20));

        return "index";
    }
}
