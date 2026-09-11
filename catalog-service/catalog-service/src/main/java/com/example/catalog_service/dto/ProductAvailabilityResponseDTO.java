package com.example.catalog_service.dto;


import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAvailabilityResponseDTO {

    private Long productId;
    private String productName;
    private BigDecimal unitPrice;
    private int stockQuantity;
    private int requestedQuantity;
    private boolean available;
}
