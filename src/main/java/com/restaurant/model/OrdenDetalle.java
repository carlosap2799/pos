package com.restaurant.model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "detalle_orden")
public class OrdenDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id", unique = false)
    private Ordenes ordenid;

    @ManyToOne
    @JoinColumn(name = "producto_id", unique = false)
    private Productosmenu productoid;

    @Column(name = "cantidad", unique = false)
    private int cantidad;

    @Column(name = "precio_unitario", unique = false)
    private Double preciounitario;

    @Column(name = "total", unique = true)
    private Double total;

    @Column(name = "notas_especiales", unique = false)
    private String notasespeciale;

    @Column(name = "fecha_creacion", unique = true)
    private Date fechacreacion;
}
