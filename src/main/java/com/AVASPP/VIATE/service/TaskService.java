package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.Task;
import com.AVASPP.VIATE.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(long id) {
        return taskRepository.findById(id).get();
    }

    public List<Task> findAllByCourseId(long id) {
        return (List<Task>) taskRepository.findAllByCourseId(id);
    }

    public void updateStatus(long id, String status) {
        taskRepository.updateStatus(id, status);
    }

}
