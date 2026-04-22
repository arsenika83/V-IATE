package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.Course;
import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.entity.Student;
import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.service.StudentService;
import com.AVASPP.VIATE.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/user_files")
public class FileController {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTaskFileStudentSide(HttpSession session, @RequestParam("file") MultipartFile file, @RequestParam("course_id") Long course_id) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Файл пуст");
            }

            User user = (User) session.getAttribute("user");

            if(user != null) {
                long user_id = user.getId();
                Student student = studentService.findOrSaveStudent(user_id);
                long group_id = student.getGroup_id();

                String originalFileName = file.getOriginalFilename();
                String fileName = System.currentTimeMillis() + "_" + originalFileName;

                Path path = Paths.get(uploadDir + "/courses/course_" + course_id + "/group_" + group_id + "/student_" + user_id + "/" + fileName);

                //Создаем директорию если не существует
                try {
                    Files.createDirectories(path.getParent());
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Files.write(path, file.getBytes());

                return ResponseEntity.ok("Файл загружен: " + fileName);
            }

            return ResponseEntity.internalServerError().body("Ошибка загрузки");

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Ошибка загрузки");
        }
    }
}
