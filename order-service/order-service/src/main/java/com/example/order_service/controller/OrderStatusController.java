package com.example.order_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderStatusController {

    @GetMapping("/api/orders/status")
    public String status(){
        return "order-service is running";
    }
}
