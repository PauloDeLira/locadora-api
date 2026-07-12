package com.lira.dev.locadora_veiculos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Locadora de Veículos API")
                        .description("Sistema de gerenciamento de clientes, veículos e locações.")
                        .version("1.0.0")
                        .contact(new Contact()
                        .name("Paulo Lira")
                        .email("paulo.lira.2002@gmail.com")
                        )
                );

    }
}
