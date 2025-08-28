package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.restaurant.model.Productosmenu;

@Repository
public interface ProductosMenuRepository extends JpaRepository<Productosmenu, Long> {
}

