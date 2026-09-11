package com.example.catalog_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogStatusController {

    @GetMapping("/api/catalog/status")
    public String status(){
        return "catalog-service is running";
    }
}
