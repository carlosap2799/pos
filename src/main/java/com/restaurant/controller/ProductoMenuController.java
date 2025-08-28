package com.restaurant.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurant.dto.ProductoMenuDTO;
import com.restaurant.model.Productosmenu;
import com.restaurant.service.ProductoMenuService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000") 
public class ProductoMenuController {

    @Autowired
    private ProductoMenuService productoMenuService;

    @PostMapping
    public ResponseEntity<Productosmenu> crearProducto(@RequestBody ProductoMenuDTO dto) {
        Productosmenu nuevoProducto = productoMenuService.crearProducto(dto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @GetMapping
    public ResponseEntity<List<ProductoMenuDTO>> listarProductos() {
        List<ProductoMenuDTO> productos = productoMenuService.listarProductos();
        return ResponseEntity.ok(productos);
    }
}
