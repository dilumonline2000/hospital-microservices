package com.hospital.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pharmacy Service API")
                        .version("1.0")
                        .description("Microservice for managing hospital pharmacy - Medicines, Stock & Prescriptions")
                        .contact(new Contact()
                                .name("Hospital Management Team")
                                .email("team@hospital.com")));
    }
}
