package com.restaurant.model;

import java.sql.Timestamp;
import java.math.BigDecimal;
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
    @JoinColumn(name = "orden_id", nullable = false)
    private Ordenes ordenid;
    
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false) 
    private Productosmenu productoid;
    
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    
    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal preciounitario;
    
    @Column(name = "total", nullable = false)
    private BigDecimal total;
    
    @Column(name = "notas_especiales")
    private String notasespeciale;
    
    @Column(name = "fecha_creacion", nullable = false)
    private Timestamp fechacreacion; // Cambié a Timestamp para incluir hora
}