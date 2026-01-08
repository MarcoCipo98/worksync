package com.marcocipo.worksync.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * OpenAPI Konfiguration für DEV.
 * Fügt einen globalen Header "X-User-Id" hinzu,
 * damit Dev-Auth über Swagger testbar ist.
 */
@Profile("dev")
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        SecurityScheme devUserHeader = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("X-User-Id");

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("DevUser", devUserHeader))
                .addSecurityItem(new SecurityRequirement().addList("DevUser"));
    }
}
