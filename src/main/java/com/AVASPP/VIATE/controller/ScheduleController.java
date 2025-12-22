package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.entity.Lesson;
import com.AVASPP.VIATE.entity.Schedule;
import com.AVASPP.VIATE.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/group")
    public String getGroupList(Model model) {
        //List<Group> groups = scheduleService.getAllGroups();
        long[] courses = {1, 2, 3, 4, 5, 6};
        List<Group> groups1 = scheduleService.getAllGroupsWithCourse(1);
        List<Group> groups2 = scheduleService.getAllGroupsWithCourse(2);
        List<Group> groups3 = scheduleService.getAllGroupsWithCourse(3);
        List<Group> groups4 = scheduleService.getAllGroupsWithCourse(4);
        List<Group> groups5 = scheduleService.getAllGroupsWithCourse(5);
        List<Group> groups6 = scheduleService.getAllGroupsWithCourse(6);

        List<List<Group>> groups_list = new ArrayList<>();
        groups_list.add(groups1);
        groups_list.add(groups2);
        groups_list.add(groups3);
        groups_list.add(groups4);
        groups_list.add(groups5);

        System.out.println(groups5.size() + " SIZE");

        for (int i = 0; i < groups5.size(); i++) {
            System.out.println(groups5.get(i).getName());
        }

        groups_list.add(groups6);

        model.addAttribute("courses", courses);
        model.addAttribute("groups_list", groups_list);

        return "group";
    }

    @GetMapping("/schedule/{group_id}")
    public String getSchedule(Model model, @PathVariable Long group_id) {
        Schedule schedule = scheduleService.save(group_id);
        String group_name = scheduleService.getGroupNameById(group_id);
        model.addAttribute("group_name", group_name);

        long[] monday = schedule.getMonday();
        Lesson[] monday_add = new Lesson[monday.length];
        for (int id = 0; id < monday.length; id++) {
            Lesson lesson = scheduleService.findLessonById(monday[id]);
            monday_add[id] = lesson;
        }
        model.addAttribute("monday_lessons", monday_add);

        long[] tuesday = schedule.getTuesday();
        Lesson[] tuesday_add = new Lesson[tuesday.length];
        for (int id = 0; id < tuesday.length; id++) {
            Lesson lesson = scheduleService.findLessonById(tuesday[id]);
            tuesday_add[id] = lesson;
        }
        model.addAttribute("tuesday_lessons", tuesday_add);

        long[] wednesday = schedule.getWednesday();
        Lesson[] wednesday_add = new Lesson[wednesday.length];
        for (int id = 0; id < wednesday.length; id++) {
            Lesson lesson = scheduleService.findLessonById(wednesday[id]);
            wednesday_add[id] = lesson;
        }
        model.addAttribute("wednesday_lessons", wednesday_add);

        long[] thursday = schedule.getThursday();
        Lesson[] thursday_add = new Lesson[thursday.length];
        for (int id = 0; id < thursday.length; id++) {
            Lesson lesson = scheduleService.findLessonById(thursday[id]);
            thursday_add[id] = lesson;
        }
        model.addAttribute("thursday_lessons", thursday_add);

        long[] friday = schedule.getFriday();
        Lesson[] friday_add = new Lesson[friday.length];
        for (int id = 0; id < friday.length; id++) {
            Lesson lesson = scheduleService.findLessonById(friday[id]);
            friday_add[id] = lesson;
        }
        model.addAttribute("friday_lessons", friday_add);

        long[] saturday = schedule.getSaturday();
        Lesson[] saturday_add = new Lesson[saturday.length];
        for (int id = 0; id < saturday.length; id++) {
            Lesson lesson = scheduleService.findLessonById(saturday[id]);
            saturday_add[id] = lesson;
        }
        model.addAttribute("saturday_lessons", saturday_add);

        return "schedule";
    }
}
