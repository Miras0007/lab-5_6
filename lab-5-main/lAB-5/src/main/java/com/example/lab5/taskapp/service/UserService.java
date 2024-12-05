package com.example.lab5.taskapp.service;

import java.time.LocalDateTime;
import com.example.lab5.taskapp.model.User;
import com.example.lab5.taskapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, username, email, encodedPassword, LocalDateTime.now(), User.Role.USER, null, null);
        return userRepository.save(user);
    }

    public User updateProfile(Long userId, String newUsername, String newEmail, String newPassword, byte[] newProfileImage) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (newUsername != null) user.setUsername(newUsername);
            if (newEmail != null) user.setEmail(newEmail);
            if (newPassword != null) user.setPasswordHash(passwordEncoder.encode(newPassword));
            if (newProfileImage != null) user.setProfileImage(newProfileImage);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
