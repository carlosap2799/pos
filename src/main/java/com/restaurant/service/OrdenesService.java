package com.restaurant.service;

import com.restaurant.model.Ordenes;
import com.restaurant.repository.OrdenesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenesService {

    private final OrdenesRepository ordenesRepository;

    public OrdenesService(OrdenesRepository ordenesRepository) {
        this.ordenesRepository = ordenesRepository;
    }

    public List<Ordenes> findAll() {
        return ordenesRepository.findAll();
    }

    public Optional<Ordenes> findById(Long id) {
        return ordenesRepository.findById(id);
    }

    public Ordenes save(Ordenes orden) {
        return ordenesRepository.save(orden);
    }

    public void delete(Long id) {
        ordenesRepository.deleteById(id);
    }
}