package com.example.order_service.client;


import com.example.order_service.dto.ProductAvailabilityResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "catalog-service")
public interface CatalogClient {

    @GetMapping("/api/catalog/products/{id}/availability")
    ProductAvailabilityResponseDTO checkAvailability(
            @PathVariable("id") Long productId,
            @RequestParam("quantity") Integer quantity
    );
}
