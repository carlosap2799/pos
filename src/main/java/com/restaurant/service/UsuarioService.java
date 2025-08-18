package com.restaurant.service;

import com.restaurant.model.Usuarios;
import com.restaurant.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuarios> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
