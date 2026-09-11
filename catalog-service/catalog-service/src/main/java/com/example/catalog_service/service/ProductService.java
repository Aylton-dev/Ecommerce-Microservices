package com.example.catalog_service.service;


import com.example.catalog_service.dto.CategoryResponseDTO;
import com.example.catalog_service.dto.ProductAvailabilityResponseDTO;
import com.example.catalog_service.dto.ProductRequestDTO;
import com.example.catalog_service.dto.ProductResponseDTO;
import com.example.catalog_service.exception.ResourceNotFoundException;
import com.example.catalog_service.model.Category;
import com.example.catalog_service.model.Product;
import com.example.catalog_service.repository.CategoryRepository;
import com.example.catalog_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponseDTO create(ProductRequestDTO request){
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + request.getCategoryId()));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setActive(true);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return toResponseDTO(savedProduct);
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAllByActiveTrue()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));

        return toResponseDTO(product);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO request) {
        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + request.getCategoryId()));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        return toResponseDTO(updatedProduct);
    }

    public void delete(Long id) {
        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));

        product.setActive(false);

        productRepository.save(product);
    }

    public ProductAvailabilityResponseDTO checkAvailability(Long id, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }

        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));

        boolean available = product.getStockQuantity() >= quantity;

        return new ProductAvailabilityResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                quantity,
                available
        );
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        Category category = product.getCategory();

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.isActive(),
                categoryResponseDTO
        );
    }
}