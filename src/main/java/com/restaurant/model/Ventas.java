package com.restaurant.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "ventas")
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)   
    private String id;

    @Column(name = "numero_venta", nullable = true)
    private Integer numeroVenta;

    @Column(name = "orden_id", nullable = false)
    private Ordenes ordenId;

    @Column(name = "usuario_id", nullable = false)
    private Usuarios usuarioId;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(name = "monto_efectivo", nullable = false)
    private Double montoEfectivo;

    @Column(name = "monto_tarjeta", nullable = false)
    private Double montoTarjeta;

    @Column(name = "monto_transferencia", nullable = false)
    private Double montoTransferencia;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "cambio", nullable = false)
    private Double cambio;

    @Column(name = "fecha_venta", nullable = false)
    private Timestamp fechaVenta;
}
