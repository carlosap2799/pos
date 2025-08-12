package com.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.sql.Timestamp;

import lombok.Data;

@Entity
@Table(name = "categoria_menu")
@Data
public class CategoriaMenu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = true)
    private String descripcion;
    
    @Column(name = "activo", nullable = false)
    private Boolean activo;
    
    @Column(name = "fecha_creacion", nullable = false)
    private Timestamp fechaCreacion;
}
