package com.example.auth_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;

    private String tokenType = "Bearer";

    public AuthResponseDTO(String token) {
        this.token = token;
        this.tokenType = "Bearer";
    }
}
