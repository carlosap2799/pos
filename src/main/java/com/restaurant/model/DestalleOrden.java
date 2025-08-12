package com.restaurant.model;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "detalle_orden")
public class DestalleOrden {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "orden_id", nullable = false)
    private Ordenes ordenId;

    @Column(name = "producto_id", nullable = false)
    private Productosmenu productoId;

    @Column(name = "paquete_id", nullable = true)
    private PaqueteMenu paqueteId;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @Column(name = "notas_especiales", nullable = false)
    private String notasEspeciales;

    @Column(name = "fecha_creacion", nullable = false)
    private Timestamp fechaCreacion;
}
