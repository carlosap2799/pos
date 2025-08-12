package com.restaurant.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "rol", nullable = false)
    private String rol;
    
    @Column(name = "activo", nullable = false)
    private Boolean activo;
    
    @Column(name = "fecha_creacion", nullable = false)
    private Timestamp fechaCreacion;   
    
    @Column(name = "fecha_actualizacion", nullable = false)
    private Timestamp fechaActualizacion;  

}
