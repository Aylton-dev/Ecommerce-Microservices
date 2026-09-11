package com.example.order_service.dto;

import jakarta.validation.Valid;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    @NotNull
    private Long userId;

    @NotEmpty
    @Valid
    private List<OrderItemRequestDTO> items;
}
