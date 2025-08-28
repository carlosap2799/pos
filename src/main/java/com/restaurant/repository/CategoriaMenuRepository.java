package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.restaurant.model.CategoriaMenu;

@Repository
public interface CategoriaMenuRepository extends JpaRepository<CategoriaMenu, Long> {
}
