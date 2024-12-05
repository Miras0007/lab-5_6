package com.example.lab5.taskapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.taskapp.model.User;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTaskNotification(User user, String taskTitle, String taskDescription) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Новая задача: " + taskTitle);
        message.setText("Здравствуйте, " + user.getUsername() + "!\n\n" +
                "Вам назначена новая задача:\n" +
                "Название: " + taskTitle + "\n" +
                "Описание: " + taskDescription + "\n\n" +
                "С уважением,\n" +
                "Команда TaskApp");
        mailSender.send(message);
    }
}
