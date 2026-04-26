package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.TaskSubmission;
import com.AVASPP.VIATE.repository.TaskSubmissionRepository;
import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TaskSubmissionService {

    @Autowired
    private final TaskSubmissionRepository taskSubmissionRepository;

    public TaskSubmissionService(TaskSubmissionRepository taskSubmissionRepository) {
        this.taskSubmissionRepository = taskSubmissionRepository;
    }

    public TaskSubmission findById(long id) {
        return taskSubmissionRepository.findById(id).get();
    }

    public TaskSubmission findByTaskIdAndUserId(long task_id, long user_id) {
        return taskSubmissionRepository.findByTaskIdAndUserId(task_id, user_id);
    }

    public void updateStatus(long id, String status) {
        taskSubmissionRepository.updateStatus(id, status);
    }

    public void save(TaskSubmission taskSubmission) {
        taskSubmissionRepository.save(taskSubmission);
    }

    public void deleteByTaskIdAndUserId(long task_id, long user_id) {
        taskSubmissionRepository.deleteByTaskIdAndUserId(task_id, user_id);
    }

    public int countByTaskIdAndUserId(long task_id, long user_id) {
        return taskSubmissionRepository.countByTaskIdAndUserId(task_id, user_id);
    }

    public String findPathByTaskIdAndUserId(long task_id, long user_id) {
        return taskSubmissionRepository.findPathByTaskIdAndUserId(task_id, user_id);
    }

    public void deleteFile(String file_path) {
        if (file_path != null) {
            try {
                Path file = Paths.get(file_path);
                Files.deleteIfExists(file);
                System.out.println("Файл удален: " + file_path);
            }
            catch (IOException e) {
                System.err.println("Ошибка удаления файла: " + e.getMessage());
            }
        }
    }
}
