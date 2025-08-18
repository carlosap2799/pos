package com.restaurant.controller;

import com.restaurant.model.Usuarios;
import com.restaurant.dto.LoginResponse;
import com.restaurant.dto.LoginRequest;
import com.restaurant.service.UsuarioService;
import com.restaurant.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // Buscar usuario por nombreUsuario
        Usuarios usuario = usuarioService.findByNombreUsuario(request.getNombreUsuario());

        String hashedPassword = passwordEncoder.encode("root_2025");
        System.out.println("Hashed password: " + hashedPassword);

        // Validar existencia y contraseña
        if (usuario == null || !passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Usuario o contraseña incorrectos", null));
        }

        // Generar token usando nombreUsuario
        String token = jwtUtil.generateToken(usuario.getNombreUsuario());

        // Devolver token y usuario en la respuesta
        return ResponseEntity.ok(new LoginResponse(token, usuario));
    }
}
