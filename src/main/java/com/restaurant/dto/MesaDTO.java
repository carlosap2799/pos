package com.restaurant.dto;

import lombok.Data;

@Data
public class MesaDTO {
    private Integer numeroMesa;
    private Integer capacidad;
    private String forma;
    private Integer posicionX;
    private Integer posicionY;
}
