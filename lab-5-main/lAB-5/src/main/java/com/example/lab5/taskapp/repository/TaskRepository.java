package com.example.lab5.taskapp.repository;

import com.example.lab5.taskapp.model.Task;
import com.example.lab5.taskapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
