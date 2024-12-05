package com.example.lab5.taskapp.controller;

import com.example.lab5.taskapp.model.Task;
import com.example.lab5.taskapp.service.TaskService;
import com.example.lab5.taskapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listTasks(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        // Логика для отображения задач с пагинацией
        Page<Task> tasks = taskService.getTasks(1L, page, 10);  // Пример с userId = 1L
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @PostMapping("/create")
    public String createTask(Task task) {
        // Логика для создания задачи
        taskService.createTask(task);
        return "redirect:/tasks/list";
    }
}
