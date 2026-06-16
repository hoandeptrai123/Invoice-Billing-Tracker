package com.example.demo.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class OrganizationResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String status;
    private LocalDateTime createdAt;
    
    
}
