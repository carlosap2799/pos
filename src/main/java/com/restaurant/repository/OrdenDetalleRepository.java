package com.restaurant.repository;

import com.restaurant.model.OrdenDetalle;
import com.restaurant.model.Ordenes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle, Long> {
    List<OrdenDetalle> findByOrdenid(Ordenes orden);
}