package com.example.auth_service.dto;

import com.example.auth_service.entity.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String username;
    private String email;
    private Role role;
    private String message;
}
