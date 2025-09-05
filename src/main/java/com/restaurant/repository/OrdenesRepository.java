package com.restaurant.repository;

import com.restaurant.model.Mesa;
import com.restaurant.model.Ordenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

public interface OrdenesRepository extends JpaRepository<Ordenes, Long> {
    Ordenes findByMesaidAndEstado(Mesa mesa, String estado);

    @Modifying
    @Transactional
    @Query(value = "CALL cerrar_orden(?1, ?2, ?3, ?4, ?5, ?6, ?7)", 
            nativeQuery = true)
    void cerrarOrden(
        Integer ordenId,
        String metodoPago,
        Double montoEfectivo,
        Double montoTarjeta,
        Double montoTransferencia,
        Double cambio,
        Integer usuarioId
    );
}