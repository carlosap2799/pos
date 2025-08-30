package com.restaurant.service;

import com.restaurant.dto.OrdenesDTO;
import com.restaurant.model.Mesa;
import com.restaurant.model.Ordenes;
import com.restaurant.model.Usuarios;
import com.restaurant.repository.MesaRepository;
import com.restaurant.repository.OrdenesRepository;
import com.restaurant.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrdenesService {

    private final OrdenesRepository ordenesRepository;
    private final MesaRepository mesaRepository;
    private final UsuarioRepository usuariosRepository;

    public Ordenes crearOrden(OrdenesDTO dto) {
        Mesa mesa = mesaRepository.findById(dto.getMesaId())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        Usuarios usuario = usuariosRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Ordenes orden = new Ordenes();
        orden.setMesaid(mesa);
        orden.setUsuarioid(usuario);
        orden.setEstado(dto.getEstado());
        orden.setSubtotal(dto.getSubtotal());
        orden.setImpuestos(dto.getImpuestos());
        orden.setTotal(dto.getTotal());
        orden.setNotas(dto.getNotas());
        orden.setFechaCreacion(LocalDateTime.now());
        orden.setFechaActualizacion(LocalDateTime.now());

        return ordenesRepository.save(orden);
    }
}
