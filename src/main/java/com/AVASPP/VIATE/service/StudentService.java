package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.Student;
import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final UserService userService;

    public StudentService(StudentRepository studentRepository, UserService userRepository) {
        this.studentRepository = studentRepository;
        this.userService = userRepository;
    }

    public Student save(User user) {
        if(studentRepository.findById(user.getId()).isPresent()) return studentRepository.findById(user.getId()).get(); //если такой профиль уже существует - его не нужно сохранять
        else return studentRepository.save(new Student(user));
    }

    public Student save(long id) {
        User user = userService.findById(id);
        if(studentRepository.findById(user.getId()).isPresent()) return studentRepository.findById(user.getId()).get();
        else return studentRepository.save(new Student(user));
    }
}
