package com.example.catalog_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private Long id;
    @NotBlank
    private String name;
    private String description;
}
