package com.restaurant.repository;

import com.restaurant.model.Ordenes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenesRepository extends JpaRepository<Ordenes, Long> {
}
