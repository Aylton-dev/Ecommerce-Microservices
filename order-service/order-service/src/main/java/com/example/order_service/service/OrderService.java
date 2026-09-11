package com.example.order_service.service;


import com.example.order_service.client.CatalogClient;
import com.example.order_service.dto.*;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderItem;
import com.example.order_service.model.OrderStatus;
import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CatalogClient catalogClient;

    public OrderService(OrderRepository orderRepository, CatalogClient catalogClient) {
        this.orderRepository = orderRepository;
        this.catalogClient = catalogClient;
    }

    public OrderResponseDTO create(OrderRequestDTO request) {
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(BigDecimal.ZERO);

        for (OrderItemRequestDTO itemRequest : request.getItems()) {
            ProductAvailabilityResponseDTO productAvailability =
                    catalogClient.checkAvailability(itemRequest.getProductId(), itemRequest.getQuantity());

            if (!productAvailability.isAvailable()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + productAvailability.getProductName());
            }

            BigDecimal subTotal = productAvailability.getUnitPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            OrderItem item = new OrderItem();
            item.setProductId(productAvailability.getProductId());
            item.setProductName(productAvailability.getProductName());
            item.setUnitPrice(productAvailability.getUnitPrice());
            item.setQuantity(itemRequest.getQuantity());
            item.setSubTotal(subTotal);

            order.addItem(item);
            order.setTotalAmount(order.getTotalAmount().add(subTotal));
        }

        Order savedOrder = orderRepository.save(order);

        return toResponseDTO(savedOrder);
    }

    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + id));

        return toResponseDTO(order);
    }

    public List<OrderResponseDTO> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private OrderResponseDTO toResponseDTO(Order order) {
        List<OrderItemResponseDTO> itemResponses = order.getItems()
                .stream()
                .map(item -> new OrderItemResponseDTO(
                        item.getId(),
                        item.getProductId(),
                        item.getProductName(),
                        item.getUnitPrice(),
                        item.getQuantity(),
                        item.getSubTotal()
                ))
                .toList();

        return new OrderResponseDTO(
                order.getId(),
                order.getUserId(),
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getStatus(),
                itemResponses
        );
    }
}
