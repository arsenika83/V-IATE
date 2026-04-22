package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.Course;
import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.repository.CourseRepository;
import com.AVASPP.VIATE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course findById(long id) {
        return courseRepository.findById(id).get();
    }

    public List<Course> getAllCoursesByGroupId(long id) {
        return (List<Course>) courseRepository.findAllByGroupId(id);
    }

    public String getTeacherNameByCourseId(long id) {
        Course course = courseRepository.findById(id).get();
        String[] full_name = userRepository.findById(course.getTeacher_id()).get().getFull_name();

        return full_name[0] + " " + full_name[1].charAt(0) + ". " + full_name[2].charAt(0) + ".";
    }
}
