package com.example.order_service.controller;


import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO create(@RequestBody @Valid OrderRequestDTO request) {
        return orderService.create(request);
    }

    @GetMapping
    public List<OrderResponseDTO> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponseDTO> findByUserId(@PathVariable Long userId) {
        return orderService.findByUserId(userId);
    }
}
