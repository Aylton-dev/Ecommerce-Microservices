package com.example.catalog_service.controller;


import com.example.catalog_service.dto.ProductAvailabilityResponseDTO;
import com.example.catalog_service.dto.ProductRequestDTO;
import com.example.catalog_service.dto.ProductResponseDTO;
import com.example.catalog_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO create(@Valid @RequestBody ProductRequestDTO request){
        return productService.create(request);
    }

    @GetMapping
    public List<ProductResponseDTO> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO request){
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @GetMapping("/{id}/availability")
    public ProductAvailabilityResponseDTO checkAvailability(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        return productService.checkAvailability(id, quantity);
    }
}
