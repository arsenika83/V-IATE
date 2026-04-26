package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.*;
import com.AVASPP.VIATE.service.StudentService;
import com.AVASPP.VIATE.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
