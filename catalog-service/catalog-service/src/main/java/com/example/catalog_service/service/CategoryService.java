package com.example.catalog_service.service;

import com.example.catalog_service.dto.CategoryRequestDTO;
import com.example.catalog_service.dto.CategoryResponseDTO;
import com.example.catalog_service.exception.ResourceNotFoundException;
import com.example.catalog_service.model.Category;
import com.example.catalog_service.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO create(CategoryRequestDTO request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category savedCategory = categoryRepository.save(category);

        return toResponseDTO(savedCategory);
    }

    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public CategoryResponseDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + id));

        return toResponseDTO(category);
    }

    public CategoryResponseDTO update(Long id, CategoryRequestDTO request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + id));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return toResponseDTO(updatedCategory);
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada com id: " + id);
        }

        categoryRepository.deleteById(id);
    }

    private CategoryResponseDTO toResponseDTO(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}