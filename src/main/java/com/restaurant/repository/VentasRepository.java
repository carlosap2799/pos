package com.restaurant.repository;

import com.restaurant.model.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Long> {
    // Puedes agregar consultas personalizadas si necesitas
}
