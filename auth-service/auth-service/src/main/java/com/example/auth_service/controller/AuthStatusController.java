package com.example.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthStatusController {

    @GetMapping("/api/auth/status")
    public String status(){
        return "auth-service is running";
    }
}
