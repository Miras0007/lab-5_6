package com.example.lab5.taskapp.controller;

import com.example.lab5.taskapp.model.Task;
import com.example.lab5.taskapp.model.User;
import com.example.lab5.taskapp.repository.TaskRepository;
import com.example.lab5.taskapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public String viewTasks(Model model, @AuthenticationPrincipal User currentUser) {
        List<Task> tasks = taskRepository.findByUser(currentUser);
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

}
