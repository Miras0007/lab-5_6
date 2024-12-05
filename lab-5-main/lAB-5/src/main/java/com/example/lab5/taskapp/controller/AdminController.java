package com.example.lab5.taskapp.controller;

import com.example.lab5.taskapp.model.*;
import com.example.lab5.taskapp.repository.*;
import com.example.lab5.taskapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/users")
    public String viewUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/tasks")
    public String viewAllTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "admin/tasks";
    }

    @GetMapping("/categories")
    public String viewCategories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin/categories";
    }

    @GetMapping("/tasks/new")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/addTask";
    }

    @PostMapping("/tasks")
    public String addTask(@ModelAttribute Task task, @RequestParam Long userId, Model model) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        task.setUser(user);
        taskRepository.save(task);

        emailService.sendTaskNotification(user, task.getTitle(), task.getDescription());

        return "redirect:/admin/tasks";
    }

}
