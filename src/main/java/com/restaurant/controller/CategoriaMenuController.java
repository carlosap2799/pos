package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import com.restaurant.dto.CategoriaMenuDTO;
import com.restaurant.model.CategoriaMenu;
import com.restaurant.service.CategoriaMenuService;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriaMenuController {

    @Autowired
    private CategoriaMenuService categoriaMenuService;

    @GetMapping
    public List<CategoriaMenuDTO> listarCategorias() {
        return categoriaMenuService.listarCategorias()
                .stream()
                .map(cat -> {
                    CategoriaMenuDTO dto = new CategoriaMenuDTO();
                    dto.setId(cat.getId());
                    dto.setNombre(cat.getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    public CategoriaMenuDTO crearCategoria(@RequestBody CategoriaMenuDTO dto) {
        CategoriaMenu categoria = categoriaMenuService.crearCategoria(dto);
        CategoriaMenuDTO resp = new CategoriaMenuDTO();
        resp.setId(categoria.getId());
        resp.setNombre(categoria.getNombre());
        return resp;
    }
}
