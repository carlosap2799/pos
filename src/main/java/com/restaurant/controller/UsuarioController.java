package com.restaurant.controller;

import com.restaurant.model.Usuarios;
import com.restaurant.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Usuarios>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }
}
