package com.restaurant.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CategoriaMenuDTO {
    private Long id;
    private String nombre; 
    private String descripcion; 
    private Boolean activo; 
    private Date fechaCreacion;
}
