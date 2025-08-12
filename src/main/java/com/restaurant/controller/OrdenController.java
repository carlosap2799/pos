package com.restaurant.controller;

import com.restaurant.model.Ordenes;
import com.restaurant.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenRepository repo;

    @GetMapping
    public List<Ordenes> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Ordenes crear(@RequestBody Ordenes orden) {
        return repo.save(orden);
    }
}
