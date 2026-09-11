package com.example.catalog_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private boolean active;
    private CategoryResponseDTO category;

}
