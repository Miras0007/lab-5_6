package com.example.lab5.taskapp.repository;

import com.example.lab5.taskapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByUserId(Long userId, Pageable pageable);
    List<Task> findByUserIdAndCategory(Long userId, Long categoryId);
}
