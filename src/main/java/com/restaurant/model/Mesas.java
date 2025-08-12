package com.restaurant.model;

import java.security.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name = "mesas")
public class Mesas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;
    
    @Column(name = "numero", nullable = false, unique = true)
    private Integer numero;
    
    @Column(name = "forma", nullable = false)
    private String forma;
    
    @Column(name = "posiciox", nullable = false)
    private Integer posiciox;
    
    @Column(name = "posiciony", nullable = false)
    private Integer posiciony;
    
    @Column(name = "estado", nullable = false)
    private String estado;
    
    @Column(name = "activo", nullable = false)
    private Boolean activo;
    
    @Column(name = "fecha_creacion", nullable = false)
    private Timestamp fechaCreacion;
}
