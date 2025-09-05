package com.restaurant.service;

import com.restaurant.model.Ventas;
import com.restaurant.repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentasService {

    private final VentasRepository ventasRepository;

    public VentasService(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    public List<Ventas> findAll() {
        return ventasRepository.findAll();
    }

    public Optional<Ventas> findById(Long id) {
        return ventasRepository.findById(id);
    }

    public Ventas save(Ventas venta) {
        return ventasRepository.save(venta);
    }

    public void delete(Long id) {
        ventasRepository.deleteById(id);
    }
}
