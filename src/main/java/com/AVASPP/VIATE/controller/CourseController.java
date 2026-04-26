package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.*;
import com.AVASPP.VIATE.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private CourseService courseService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskSubmissionService taskSubmissionService;

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
                List<String> taskStatuses = new ArrayList<>();

                int[] task_count = new int[tasks.size()];
                List<TaskSubmission> taskSubmissions = new ArrayList<>();
                for(int i = 0; i < tasks.size(); i++) {
                    task_count[i] = i;
                    TaskSubmission submission = taskSubmissionService.findByTaskIdAndUserId(tasks.get(i).getId(), user_id);
                    taskSubmissions.add(submission);
                    taskStatuses.add(submission.getStatus());
                }

                model.addAttribute("tasks", tasks);
                model.addAttribute("taskCount", task_count);
                model.addAttribute("taskSubmissions", taskSubmissions);
                model.addAttribute("taskStatuses", taskStatuses);
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

    @PostMapping("/task/add")
    public String addTask(HttpSession session, @RequestParam String title, @RequestParam String description, @RequestParam Long courseId) {
        User user = (User) session.getAttribute("user");
        if(user != null) {
            if(user.getRole().equals("TEACHER")) {
                Task task = new Task();
                task.setTitle(title);
                task.setDescription(description);
                taskService.save(task);
                return "redirect:/course?id=" + courseId;
            }
            return "redirect:/course_lobby";
        }
        return "redirect:/login";
    }

    @PostMapping("/task/{taskId}/submit")
    public String uploadTaskFileStudentSide(HttpSession session,
                                                            @PathVariable Long taskId,
                                                            @RequestParam("file") MultipartFile file,
                                                            @RequestParam("course_id") Long course_id) {
        try {
            if (file.isEmpty()) {
                return "redirect:/course?id=" + course_id;
            }

            User user = (User) session.getAttribute("user");

            if(user != null) {
                long user_id = user.getId();
                Student student = studentService.findOrSaveStudent(user_id);
                long group_id = student.getGroup_id();

                String fileName = file.getOriginalFilename();

                Path path = Paths.get(uploadDir + "/courses/course_" + course_id + "/task_" + taskId + "/group_" + group_id + "/student_" + user_id + "/" + fileName);

                // создаем директорию если не существует
                try {
                    Files.createDirectories(path.getParent());
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if(taskSubmissionService.countByTaskIdAndUserId(taskId, user_id) > 0) {
                    taskSubmissionService.deleteFile(taskSubmissionService.findPathByTaskIdAndUserId(taskId, user_id));
                    taskSubmissionService.deleteByTaskIdAndUserId(taskId, user_id);
                }

                Files.write(path, file.getBytes());

                Task task = taskService.findById(taskId);
                TaskSubmission taskSubmission = new TaskSubmission(task.getId(), user_id, fileName, path.toString());
                taskSubmissionService.save(taskSubmission);

                taskService.updateSubmissionIds(task.getId(), taskSubmission.getId());

                return "redirect:/course?id=" + course_id;
            }

            return "Ошибка загрузки";

        } catch (IOException e) {
            return "Ошибка загрузки";
        }
    }

    @PutMapping("/update_task_status/{id}")
    public String update_task_status(HttpSession session, Model model, @PathVariable Long id, @RequestParam String status) {

        taskSubmissionService.updateStatus(id, status);

        return "update_task_status";
    }


}
