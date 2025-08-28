package com.restaurant.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.dto.CategoriaMenuDTO;
import com.restaurant.model.CategoriaMenu;
import com.restaurant.repository.CategoriaMenuRepository;

@Service
public class CategoriaMenuService {

    @Autowired
    private CategoriaMenuRepository categoriaMenuRepository;

    public List<CategoriaMenu> listarCategorias() {
        return categoriaMenuRepository.findAll();
    }

    public CategoriaMenu crearCategoria(CategoriaMenuDTO dto) {
        CategoriaMenu categoria = new CategoriaMenu();
        categoria.setNombre(dto.getNombre().toUpperCase());
        categoria.setActivo(true);
        categoria.setDescripcion(dto.getDescripcion() != null ? dto.getDescripcion().toUpperCase() : null);
        categoria.setFechaCreacion(Date.valueOf(LocalDate.now()));
        return categoriaMenuRepository.save(categoria);
    }
}
