package com.restaurant.controller;

import com.restaurant.model.Ordenes;
import com.restaurant.service.OrdenesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenesController {

    private final OrdenesService ordenesService;

    public OrdenesController(OrdenesService ordenesService) {
        this.ordenesService = ordenesService;
    }

    @GetMapping
    public List<Ordenes> getAll() {
        return ordenesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ordenes> getById(@PathVariable Long id) {
        return ordenesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ordenes create(@RequestBody Ordenes orden) {
        return ordenesService.save(orden);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ordenes> update(@PathVariable Long id, @RequestBody Ordenes orden) {
        return ordenesService.findById(id).map(existing -> {
            existing.setTotal(orden.getTotal());
            existing.setEstado(orden.getEstado());
            return ResponseEntity.ok(ordenesService.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ordenesService.findById(id).map(o -> {
            ordenesService.delete(id);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
