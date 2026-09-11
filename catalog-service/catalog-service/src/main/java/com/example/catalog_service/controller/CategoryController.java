package com.example.catalog_service.controller;

import com.example.catalog_service.dto.CategoryRequestDTO;
import com.example.catalog_service.dto.CategoryResponseDTO;
import com.example.catalog_service.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO create(@Valid @RequestBody CategoryRequestDTO request){
        return categoryService.create(request);
    }
    @GetMapping
    public List<CategoryResponseDTO> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO update(@PathVariable Long id, @RequestBody @Valid CategoryRequestDTO request) {
        return categoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

}
