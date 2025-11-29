package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.entity.Lesson;
import com.AVASPP.VIATE.entity.Schedule;
import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private final ScheduleRepository scheduleRepository;

    @Autowired
    private final LessonService lessonService;

    @Autowired
    private final GroupService groupService;

    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleRepository lessonRepository, LessonService lessonService, GroupService groupService) {
        this.scheduleRepository = scheduleRepository;

        this.groupService = groupService;
        this.lessonService = lessonService;
    }

    public Schedule save(long group_id) {
        Schedule schedule = new Schedule(group_id);

        long[][] lessons = new long[6][1];
        String[] days = {"ПОНЕДЕЛЬНИК","ВТОРНИК", "СРЕДА", "ЧЕТВЕРГ", "ПЯТНИЦА", "СУББОТА"};

        for (int day = 0; day < 6; day++) {
            List<Long> lessons_day = lessonService.findAllLessonsByGroupId(group_id, days[day]);
            lessons[day] = new long[lessons_day.size()];
            for (int i = 0; i < lessons_day.size(); i++) {
                lessons[day][i] = lessons_day.get(i);
            }
        }

        schedule.setMonday(lessons[0]);
        schedule.setTuesday(lessons[1]);
        schedule.setWednesday(lessons[2]);
        schedule.setThursday(lessons[3]);
        schedule.setFriday(lessons[4]);
        schedule.setSaturday(lessons[5]);

        return scheduleRepository.save(schedule);
    }

    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    public String getGroupNameById(long id) {
        return groupService.getGroupNameById(id);
    }

    public Lesson findLessonById(long id) {
        return lessonService.findById(id);
    }
}
