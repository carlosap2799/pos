package com.restaurant.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "arqueo_caja")
public class ArqueoCaja {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false, nullable = false)
    private Long id;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "total_efectivo", nullable = false)
    private Double totalEfectivo;

    @Column(name = "total_tarjeta", nullable = false)
    private Double totalTarjeta;

    @Column(name = "total_transferencia", nullable = false)
    private Double totalTransferencia;
}
