package com.restaurant.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.dto.ProductoMenuDTO;
import com.restaurant.model.CategoriaMenu;
import com.restaurant.model.Productosmenu;
import com.restaurant.repository.CategoriaMenuRepository;
import com.restaurant.repository.ProductosMenuRepository;

@Service
public class ProductoMenuService {

    @Autowired
    private ProductosMenuRepository productosMenuRepository;

    @Autowired
    private CategoriaMenuRepository categoriaMenuRepository;

    public Productosmenu crearProducto(ProductoMenuDTO dto) {
        CategoriaMenu categoria = categoriaMenuRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Productosmenu producto = new Productosmenu();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(categoria);
        producto.setTipo(dto.getTipo());
        producto.setStock(dto.getStock());
        producto.setImagenUrl(dto.getImagenUrl());
        producto.setActivo(true);
        producto.setFechaCreacion(Date.valueOf(LocalDate.now()));
        producto.setFechaActualizacion(Date.valueOf(LocalDate.now()));

        return productosMenuRepository.save(producto);
    }

    public List<ProductoMenuDTO> listarProductos() {
        return productosMenuRepository.findAll()
            .stream()
            .map(p -> {
                ProductoMenuDTO dto = new ProductoMenuDTO();
                dto.setId(p.getId());
                dto.setNombre(p.getNombre());
                dto.setDescripcion(p.getDescripcion());
                dto.setPrecio(p.getPrecio());
                dto.setCategoriaId(p.getCategoria().getId());
                dto.setTipo(p.getTipo());
                dto.setStock(p.getStock());
                dto.setImagenUrl(p.getImagenUrl());
                return dto;
            })
            .collect(Collectors.toList());
    }
}
