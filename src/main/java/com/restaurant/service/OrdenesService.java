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

        // Revisar si ya existe una orden activa en esta mesa
        Ordenes ordenActiva = ordenesRepository.findByMesaidAndEstado(mesa, "abierta");

        if (ordenActiva != null) {
            // Sumar lo nuevo al total existente
            ordenActiva.setSubtotal(ordenActiva.getSubtotal().add(dto.getSubtotal()));
            ordenActiva.setImpuestos(ordenActiva.getImpuestos().add(dto.getImpuestos()));
            ordenActiva.setTotal(ordenActiva.getTotal().add(dto.getTotal()));
            ordenActiva.setNotas(dto.getNotas()); // opcional, podrías concatenar en vez de reemplazar
            ordenActiva.setFechaActualizacion(LocalDateTime.now());

            return ordenesRepository.save(ordenActiva);
        }

        // Crear nueva orden si no existe
        Ordenes orden = new Ordenes();
        orden.setMesaid(mesa);
        orden.setUsuarioid(usuario);
        orden.setEstado("abierta");
        orden.setSubtotal(dto.getSubtotal());
        orden.setImpuestos(dto.getImpuestos());
        orden.setTotal(dto.getTotal());
        orden.setNotas(dto.getNotas());
        orden.setFechaCreacion(LocalDateTime.now());
        orden.setFechaActualizacion(LocalDateTime.now());

        Ordenes nuevaOrden = ordenesRepository.save(orden);

        // Marcar mesa como ocupada
        mesa.setEstado("ocupada");
        mesaRepository.save(mesa);

        return nuevaOrden;
    }

    // 👇 Nuevo método para obtener la orden activa de una mesa
    public Ordenes obtenerOrdenActiva(Long mesaId) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        return ordenesRepository.findByMesaidAndEstado(mesa, "abierta");
    }
}

