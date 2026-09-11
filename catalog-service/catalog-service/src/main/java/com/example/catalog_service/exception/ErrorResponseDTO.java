package com.example.catalog_service.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDTO (
    LocalDateTime timestanp,
    int status,
    String error,
    String message,
    String path,
    List<String> details
){
}
