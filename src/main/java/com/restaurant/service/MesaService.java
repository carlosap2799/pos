package com.restaurant.service;

import com.restaurant.dto.MesaDTO;
import com.restaurant.model.Mesa;
import com.restaurant.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public Mesa crearMesa(MesaDTO dto) {
        Mesa mesa = new Mesa();
        mesa.setNumeroMesa(dto.getNumeroMesa());
        mesa.setCapacidad(dto.getCapacidad());
        mesa.setForma(dto.getForma() != null ? dto.getForma() : "redonda");
        mesa.setPosicionX(dto.getPosicionX());
        mesa.setPosicionY(dto.getPosicionY());
        mesa.setEstado("disponible");
        mesa.setActivo(true);
        mesa.setFechaCreacion(LocalDateTime.now());
        mesa.setFechaActualizacion(LocalDateTime.now());
        return mesaRepository.save(mesa);
    }

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Mesa actualizarMesaPosicion(Long id, MesaDTO dto) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        if (dto.getPosicionX() != null) mesa.setPosicionX(dto.getPosicionX());
        if (dto.getPosicionY() != null) mesa.setPosicionY(dto.getPosicionY());

        mesa.setFechaActualizacion(LocalDateTime.now());
        return mesaRepository.save(mesa);
    }

    //  Editar mesa completa
    public Mesa editarMesa(Long id, MesaDTO dto) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        if (dto.getNumeroMesa() != null) mesa.setNumeroMesa(dto.getNumeroMesa());
        if (dto.getCapacidad() != null) mesa.setCapacidad(dto.getCapacidad());
        if (dto.getForma() != null) mesa.setForma(dto.getForma());
        if (dto.getPosicionX() != null) mesa.setPosicionX(dto.getPosicionX());
        if (dto.getPosicionY() != null) mesa.setPosicionY(dto.getPosicionY());

        mesa.setFechaActualizacion(LocalDateTime.now());
        return mesaRepository.save(mesa);
    }

    //  solo cambia estado activo
    public void desactivarMesa(Long id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        mesa.setActivo(false);
        mesa.setFechaActualizacion(LocalDateTime.now());
        mesaRepository.save(mesa);
    }

    // 🔹 Hard delete (elimina de la BD)
    public void eliminarMesa(Long id) {
        if (!mesaRepository.existsById(id)) {
            throw new RuntimeException("Mesa no encontrada");
        }
        mesaRepository.deleteById(id);
    }
}
