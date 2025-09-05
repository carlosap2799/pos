package com.restaurant.controller;

import com.restaurant.dto.OrdenesDTO;
import com.restaurant.dto.PagoDTO;
import com.restaurant.model.OrdenDetalle;
import com.restaurant.model.Ordenes;
import com.restaurant.repository.OrdenDetalleRepository;
import com.restaurant.service.OrdenesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class OrdenesController {
    
    private final OrdenesService ordenesService;
    private final OrdenDetalleRepository ordenDetalleRepository;

    @PostMapping
    public ResponseEntity<Ordenes> crearOrden(@RequestBody OrdenesDTO dto) {
        Ordenes nuevaOrden = ordenesService.crearOrden(dto);
        return ResponseEntity.ok(nuevaOrden);
    }

    @GetMapping("/mesa/{mesaId}")
    public ResponseEntity<Map<String, Object>> obtenerOrdenPorMesa(@PathVariable Long mesaId) {
        try {
            Ordenes orden = ordenesService.obtenerOrdenActivaPorMesa(mesaId);
            
            if (orden == null) {
                return ResponseEntity.ok(Map.of("orden", (Object) null, "detalles", List.of()));
            }

            // Obtener detalles de la orden
            List<OrdenDetalle> detalles = ordenDetalleRepository.findByOrdenid(orden);
            
            Map<String, Object> response = new HashMap<>();
            response.put("orden", orden);
            response.put("detalles", detalles);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.ok(Map.of("orden", (Object) null, "detalles", List.of()));
        }
    }

    @PostMapping("/{id}/cerrar")
    public ResponseEntity<Map<String, String>> cerrarOrden(
            @PathVariable("id") int ordenId,
            @RequestBody PagoDTO pago
    ) {
        ordenesService.cerrarOrden(
            ordenId,
            pago.getMetodoPago(),
            pago.getMontoEfectivo(),
            pago.getMontoTarjeta(),
            pago.getMontoTransferencia(),
            pago.getCambio(),
            pago.getUsuarioId()
        );

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Orden cerrada con éxito ✅");

        return ResponseEntity.ok(response);
    }
}