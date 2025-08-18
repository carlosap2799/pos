# Proyecto Restaurante

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

## 🔐 Usuario administrador por defecto
Al iniciar la aplicación, si no existe un usuario administrador, se crea automáticamente:
Usuario: root
Contraseña: root_2025

Esto permite acceder al sistema sin tener que crear un usuario manualmente.

## 🔑 Uso del token de sesión
Para probar los endpoints del backend desde herramientas como Postman, se debe usar el token JWT obtenido al iniciar sesión:
- Inicia sesión con el usuario administrador (root / root_2025) en /auth/login.
- Copia el valor del token devuelto en la respuesta.
- Inclúyelo en las solicitudes a los servicios protegidos mediante el header:
-  Authorization: Bearer <TU_TOKEN_AQUI>

Esto permite acceder a los endpoints que requieren autenticación.
Para el front crear un archivo en raíz llamado .env.local y agregar:
NEXT_PUBLIC_API_URL=http://localhost:8080