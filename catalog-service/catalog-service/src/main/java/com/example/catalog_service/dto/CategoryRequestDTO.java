package com.example.catalog_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

    @NotBlank(message = "Category name cannot be blank")
    private String name;

    private String description;
}
