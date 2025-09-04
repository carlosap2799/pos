package com.restaurant.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrdenesDTO {
    private Long mesaId;
    private Long usuarioId;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private String notas;
    private List<ProductoOrdenDTO> productos; // Lista de productos
    
    @Data
    public static class ProductoOrdenDTO {
        private Long productoId;
        private Integer cantidad;
        private String notasEspeciales;
    }
}