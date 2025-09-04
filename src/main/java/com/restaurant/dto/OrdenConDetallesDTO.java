package com.restaurant.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdenConDetallesDTO {
    private Long ordenId;
    private Long mesaId;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private String notas;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    private List<DetalleDTO> detalles;

    @Data
    public static class DetalleDTO {
        private Long detalleId;
        private Long productoId;
        private String nombreProducto;
        private int cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal total;
    }
}
