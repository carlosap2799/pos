package com.restaurant.dto;

import com.restaurant.model.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Usuarios user;
}
