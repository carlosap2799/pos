package com.restaurant.service;

import com.restaurant.dto.OrdenesDTO;
import com.restaurant.model.*;
import com.restaurant.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Date;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class OrdenesService {

    private final OrdenesRepository ordenesRepository;
    private final OrdenDetalleRepository ordenDetalleRepository;
    private final MesaRepository mesaRepository;
    private final UsuarioRepository usuariosRepository;
    private final ProductosMenuRepository productosMenuRepository;

    @Transactional
    public Ordenes crearOrden(OrdenesDTO dto) {
        Mesa mesa = mesaRepository.findById(dto.getMesaId())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        Usuarios usuario = usuariosRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Revisar si ya existe una orden activa en esta mesa
        Ordenes ordenActiva = ordenesRepository.findByMesaidAndEstado(mesa, "abierta");

        if (ordenActiva != null) {
            // Actualizar orden existente
            ordenActiva.setSubtotal(ordenActiva.getSubtotal().add(dto.getSubtotal()));
            ordenActiva.setImpuestos(ordenActiva.getImpuestos().add(dto.getImpuestos()));
            ordenActiva.setTotal(ordenActiva.getTotal().add(dto.getTotal()));
            ordenActiva.setNotas(dto.getNotas());
            ordenActiva.setFechaActualizacion(LocalDateTime.now());

            Ordenes ordenGuardada = ordenesRepository.save(ordenActiva);
            
            // Guardar nuevos detalles de productos
            guardarDetallesOrden(ordenGuardada, dto);
            
            return ordenGuardada;
        }

        // Crear nueva orden si no existe
        Ordenes orden = new Ordenes();
        orden.setMesaid(mesa);
        orden.setUsuarioid(usuario);
        orden.setEstado("abierta");
        orden.setSubtotal(dto.getSubtotal());
        orden.setImpuestos(dto.getImpuestos());
        orden.setTotal(dto.getTotal());
        orden.setNotas(dto.getNotas());
        orden.setFechaCreacion(LocalDateTime.now());
        orden.setFechaActualizacion(LocalDateTime.now());

        Ordenes nuevaOrden = ordenesRepository.save(orden);
        
        // Guardar detalles de productos
        guardarDetallesOrden(nuevaOrden, dto);

        // Marcar mesa como ocupada
        mesa.setEstado("ocupada");
        mesaRepository.save(mesa);

        return nuevaOrden;
    }

    private void guardarDetallesOrden(Ordenes orden, OrdenesDTO dto) {
        if (dto.getProductos() != null && !dto.getProductos().isEmpty()) {
            for (OrdenesDTO.ProductoOrdenDTO productoDTO : dto.getProductos()) {
                Productosmenu producto = productosMenuRepository.findById(productoDTO.getProductoId())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productoDTO.getProductoId()));

                OrdenDetalle detalle = new OrdenDetalle();
                detalle.setOrdenid(orden);
                detalle.setProductoid(producto);
                detalle.setCantidad(productoDTO.getCantidad());
                BigDecimal precioUnitario = BigDecimal.valueOf(producto.getPrecio());
                detalle.setPreciounitario(precioUnitario);
                detalle.setTotal(precioUnitario.multiply(BigDecimal.valueOf(productoDTO.getCantidad())));
                detalle.setNotasespeciale(productoDTO.getNotasEspeciales());
                detalle.setFechacreacion(new Timestamp(System.currentTimeMillis()));

                ordenDetalleRepository.save(detalle);
            }
        }
    }

    public Ordenes obtenerOrdenActiva(Long mesaId) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        return ordenesRepository.findByMesaidAndEstado(mesa, "abierta");
    }
    
    // Método adicional que usa el controller
    public Ordenes obtenerOrdenActivaPorMesa(Long mesaId) {
        return obtenerOrdenActiva(mesaId);
    }

    @Transactional
    public void cerrarOrden(
        int ordenId,
        String metodoPago,
        double montoEfectivo,
        double montoTarjeta,
        double montoTransferencia,
        double cambio,
        int usuarioId
    ) {
        ordenesRepository.cerrarOrden(
            ordenId, metodoPago, montoEfectivo, montoTarjeta, montoTransferencia, cambio, usuarioId
        );
    }
}