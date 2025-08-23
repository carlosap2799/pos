package com.restaurant.dto;

import lombok.Data;

@Data
public class ProductoMenuDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Long categoriaId; 
    private String tipo;
    private Integer stock;
    private String imagenUrl;
}
