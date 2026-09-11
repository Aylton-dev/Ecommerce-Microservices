package com.example.catalog_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    private String description;

    @NotNull(message = "price cannot be null")
    @Positive(message = "price must be positive")
    private BigDecimal price;

    @NotNull(message = "stock cannot be null")
    @PositiveOrZero(message = "stock must be positive")
    private Integer stockQuantity;

    @NotNull(message = "category cannot be null")
    private Long categoryId;
}
