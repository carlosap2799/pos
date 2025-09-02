package com.restaurant.controller;

import com.restaurant.dto.OrdenesDTO;
import com.restaurant.model.Ordenes;
import com.restaurant.service.OrdenesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class OrdenesController {

    private final OrdenesService ordenesService;

    @PostMapping
    public ResponseEntity<Ordenes> crearOrden(@RequestBody OrdenesDTO dto) {
        Ordenes nuevaOrden = ordenesService.crearOrden(dto);
        return ResponseEntity.ok(nuevaOrden);
    }

    @GetMapping("/mesa/{mesaId}")
    public ResponseEntity<Ordenes> obtenerOrdenActiva(@PathVariable Long mesaId) {
        Ordenes orden = ordenesService.obtenerOrdenActiva(mesaId);
        return ResponseEntity.ok(orden);
    }
}
