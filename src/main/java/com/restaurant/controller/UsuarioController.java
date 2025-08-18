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

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getUsuarioById(@PathVariable Long id) {
        Usuarios usuario = usuarioService.getUsuarioById(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Usuarios> createUsuario(@RequestBody Usuarios usuario) {
        return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> updateUsuario(@PathVariable Long id, @RequestBody Usuarios usuarioDetails) {
        Usuarios updatedUsuario = usuarioService.updateUsuario(id, usuarioDetails);
        return updatedUsuario != null ? ResponseEntity.ok(updatedUsuario) : ResponseEntity.notFound().build();
    }
}
