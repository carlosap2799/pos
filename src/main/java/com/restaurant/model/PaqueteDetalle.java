package com.restaurant.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "paquete_detalle")

public class PaqueteDetalle{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "paquete_id", nullable = false)
    private PaqueteMenu paqueteId;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Productosmenu productoId;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}
