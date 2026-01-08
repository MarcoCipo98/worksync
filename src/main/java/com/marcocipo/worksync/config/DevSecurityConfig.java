package com.marcocipo.worksync.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * DEV Security Konfiguration.
 * Swagger Ui und H2 ohne Login nutzbar.
 * Gilt nur wenn "dev" aktiv ist.
 */
@Profile("dev")
@Configuration
public class DevSecurityConfig {

    @Bean
    public SecurityFilterChain devFilterChain(HttpSecurity http) throws Exception {
        http
                //deaktivert für jetzt, da es POST/PUT/DELETE blockiert.
                .csrf(csrf -> csrf.disable())

                //Wird deaktivert damit der browser die anzeige nicht blockt.
                .headers(headers -> headers.frameOptions(frame ->frame.disable()))

                // ohne auth erreichbar sein.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/h2-console/**"
                        ).permitAll()

                        // In der dev wird alles durchgelassen, später mit .authenticated
                        .anyRequest().permitAll()
                )

                // Deaktiviert Browser-Popups (Basic Auth) und Form-Login-Seite.
                .httpBasic(basic -> basic.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }
}
