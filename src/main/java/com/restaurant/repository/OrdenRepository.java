package com.restaurant.repository;

import com.restaurant.model.Ordenes;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrdenRepository extends JpaRepository<Ordenes, UUID> {}