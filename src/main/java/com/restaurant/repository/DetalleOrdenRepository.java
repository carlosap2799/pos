package com.restaurant.repository;

import com.restaurant.model.OrdenDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<OrdenDetalle, Long> {
    List<OrdenDetalle> findByOrden_Id(Long ordenId);
}
