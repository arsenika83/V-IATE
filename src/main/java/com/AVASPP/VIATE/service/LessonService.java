package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.Lesson;
import com.AVASPP.VIATE.repository.LessonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {
    @Autowired
    private final LessonRepository lessonRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Long> findAllLessonsByGroupId(long group_id, String day) {
        String query = "SELECT id FROM Lesson WHERE group_id = " + group_id + " AND day = '" + day + "'";
        List<Long> lessons = entityManager.createQuery(query, Long.class).getResultList();

        return lessons;
    }

    public Lesson findById(long id) {
        return lessonRepository.findById(id).get();
    }

    public ArrayList<String> findInfoById(long id) {
        ArrayList<String> info = new ArrayList<>();
        Lesson lesson = lessonRepository.findById(id).get();

        String day = lesson.getDay();
        String time = lesson.getTime();
        String type = lesson.getType();
        String week = lesson.getWeek();
        String name = lesson.getName();
        String teacher_name = lesson.getTeacher_name();
        String classroom = lesson.getClassroom();

        info.add(day);
        info.add(time);
        info.add(type);
        info.add(week);
        info.add(name);
        info.add(teacher_name);
        info.add(classroom);

        return info;
    }

}
