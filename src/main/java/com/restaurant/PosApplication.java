package com.restaurant;

import com.restaurant.model.Usuarios;
import com.restaurant.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UsuarioService usuarioService) {
        return args -> {
            if (usuarioService.findByNombreUsuario("root") == null) {
                Usuarios root = new Usuarios();
                root.setNombreUsuario("root");
                root.setNombreCompleto("Root User");
                root.setEmail("root@test.com");
                root.setRol("admin");
                root.setActivo(true);
                root.setPasswordHash("root_2025");
                usuarioService.saveUsuario(root);
                System.out.println("Usuario root creado ✅");
            }
        };
    }
}