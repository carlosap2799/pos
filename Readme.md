# Proyecto Restaurante (Spring Boot + PostgreSQL)

## 🚀 Requisitos
- Java 17+
- Maven 3.9+
- PostgreSQL 15+

## ⚙️ Configuración de la Base de Datos
1. Crear la base de datos:
   CREATE DATABASE restaurante;
   CREATE SCHEMA restaurant;
Configurar credenciales en src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/restaurante
spring.datasource.username=postgres
spring.datasource.password=123456
spring.jpa.properties.hibernate.default_schema=restaurant

🔐 Seguridad
El sistema usa Basic Auth.

Usuario inicial: admin

Password inicial: 1234
mvn spring-boot:run
Acceso API en: http://localhost:8080/usuarios/getall