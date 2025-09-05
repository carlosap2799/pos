package com.restaurant.controller;

import com.restaurant.model.Ventas;
import com.restaurant.service.VentasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    // Obtener todas las ventas
    @GetMapping
    public List<Ventas> getAllVentas() {
        return ventasService.findAll();
    }

    // Obtener una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ventas> getVentaById(@PathVariable Long id) {
        return ventasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva venta
    @PostMapping
    public Ventas createVenta(@RequestBody Ventas venta) {
        return ventasService.save(venta);
    }

    // Eliminar una venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
