package com.restaurant.dto;

import lombok.Data;

@Data
public class PagoDTO {
    private String metodoPago;
    private double montoEfectivo;
    private double montoTarjeta;
    private double montoTransferencia;
    private double cambio;
    private int usuarioId;
}
