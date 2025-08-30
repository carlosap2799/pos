package com.restaurant.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrdenesDTO {
    private Long mesaId;
    private Long usuarioId;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private String notas;
    private List<ProductoOrdenDTO> productos;

    @Data
    public static class ProductoOrdenDTO {
        private Long productoId;
        private Integer cantidad;
    }
}

