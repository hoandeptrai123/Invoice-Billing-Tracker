package com.example.demo.dto;
import lombok.Data;
@Data
public class RegisterRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long organizationId;
    
}
