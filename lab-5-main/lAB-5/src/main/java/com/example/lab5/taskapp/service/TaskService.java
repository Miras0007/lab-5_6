package com.example.lab5.taskapp.service;

import com.example.lab5.taskapp.model.Task;
import com.example.lab5.taskapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Page<Task> getTasks(Long userId, int page, int size) {
        return taskRepository.findByUserId(userId, PageRequest.of(page, size));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
}
