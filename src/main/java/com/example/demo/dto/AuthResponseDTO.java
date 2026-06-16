package com.example.demo.dto;
import lombok.Data;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String accessToken;
    private String message;
}
