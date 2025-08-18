package com.restaurant.config;

import com.restaurant.security.JwtFilter;
import com.restaurant.security.JwtUtil;
import com.restaurant.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public SecurityConfig(JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtFilter jwtFilter = new JwtFilter(jwtUtil, usuarioService);

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()      // Login público
                .requestMatchers("/usuarios/create").denyAll() // Crear usuarios manualmente ahora está bloqueado
                .anyRequest().authenticated()                 // Todo lo demás requiere JWT
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
