package com.restaurant.model;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "arqueo_caja")
public class ArqueoCaja {
    
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "Fecha_inicio", nullable = false)
    private Timestamp fechaInicio;

    @Column(name = "Fecha_fin", nullable = false)
    private Timestamp fechaFin;

    @Column(name = "total_ventas", nullable = false)
    private Double totalVentas;

    @Column(name = "total_efectivo", nullable = false)
    private Double totalEfectivo;

    @Column(name = "total_tarjeta", nullable = false)
    private Double totalTarjeta;

    @Column(name = "total_transferencia", nullable = false)
    private Double totalTransferencia;

    @Column(name = "cerrado_por", nullable = false)
    private String cerradoPor;
}
