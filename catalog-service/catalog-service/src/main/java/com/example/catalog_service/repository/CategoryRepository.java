package com.example.catalog_service.repository;

import com.example.catalog_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
