package com.example.lab5.taskapp.controller;

import com.example.lab5.taskapp.model.User;
import com.example.lab5.taskapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String viewProfile() {
        // Логика для отображения профиля
        return "user/profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam String newUsername, @RequestParam String newEmail,
                                @RequestParam String newPassword) {
        // Логика для обновления данных профиля
        return "redirect:/user/profile";
    }

    @PostMapping("/uploadPhoto")
    public String uploadPhoto(@RequestParam("photo") byte[] photo) {
        // Логика для загрузки фото
        return "redirect:/user/profile";
    }
}
