package com.example.lab5.taskapp.repository;

import com.example.lab5.taskapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
