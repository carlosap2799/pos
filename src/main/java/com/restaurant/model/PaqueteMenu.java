package com.restaurant.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "paquete_menu")
public class PaqueteMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;  

    @Column(name = "descripcion", nullable = true)
    private String descripcion;
    
    @Column(name = "precio", nullable = false)
    private Double precio;

}
