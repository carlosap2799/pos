package com.restaurant.service;

import com.restaurant.model.Usuarios;
import com.restaurant.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Usuarios> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuarios getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuarios saveUsuario(Usuarios usuario) {
        usuario.setPasswordHash(passwordEncoder.encode(usuario.getPasswordHash()));
        usuario.setFechaActualizacion(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // ⚡ Métodos que estaban faltando para JWT y root inicial
    public Usuarios findByNombreUsuario(String username) {
        return usuarioRepository.findByNombreUsuario(username);
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public Usuarios updateUsuario(Long id, Usuarios usuarioActualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombreUsuario(usuarioActualizado.getNombreUsuario());
            usuario.setNombreCompleto(usuarioActualizado.getNombreCompleto());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setRol(usuarioActualizado.getRol());
            usuario.setActivo(usuarioActualizado.getActivo());
            // Si envían nueva contraseña
            if (usuarioActualizado.getPasswordHash() != null && !usuarioActualizado.getPasswordHash().isEmpty()) {
                usuario.setPasswordHash(passwordEncoder.encode(usuarioActualizado.getPasswordHash()));
            }
            usuario.setFechaActualizacion(LocalDateTime.now());
            return usuarioRepository.save(usuario);
        }).orElse(null);
    }
}