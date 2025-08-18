package com.restaurant.repository;

import com.restaurant.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    Usuarios findByNombreUsuario(String nombreUsuario);
}
