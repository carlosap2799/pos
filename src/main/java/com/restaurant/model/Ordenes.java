package com.restaurant.model;

import java.sql.Timestamp;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ordenes")
public class Ordenes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "numero_orden", nullable = false)
    private Integer numeroOrden;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesas mesa;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "subtotal", nullable = false)
    private double subtotal;
    
    @Column(name = "impuestos", nullable = false)
    private Double impuestos;
    
    @Column(name = "total", nullable = false)
    private Double total;
    
    @Column(name = "notas")
    private String notas;

    @Column(name = "fecha_creacion", nullable = false)
    private Timestamp fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private Timestamp fechaActualizacion;
}