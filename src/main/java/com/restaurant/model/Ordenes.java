package com.restaurant.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@Entity
@Data
@Table(name = "ordenes")
@NoArgsConstructor
@AllArgsConstructor
public class Ordenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "numero_orden_dia")
    private Integer numeroOrdenDia;

    @ManyToOne
    @JoinColumn(name = "mesa_id", referencedColumnName = "id")
    private Mesa mesaid;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuarios usuarioid;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "impuestos")
    private BigDecimal impuestos;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "notas")
    private String notas;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}