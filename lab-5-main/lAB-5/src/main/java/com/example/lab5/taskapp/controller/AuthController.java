package com.example.lab5.taskapp.controller;


import com.example.lab5.taskapp.model.User;
import com.example.lab5.taskapp.repository.UserRepository;
import com.example.lab5.taskapp.repository.RoleRepository;
import com.example.lab5.taskapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Имя пользователя уже существует.");
            return "register";
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        user.setAccountCreated(LocalDateTime.now());
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Роль USER не найдена"));
        user.setRole(userRole);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
