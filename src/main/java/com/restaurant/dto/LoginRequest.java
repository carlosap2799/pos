package com.restaurant.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String nombreUsuario;
    private String password;
}
