package com.berat.ticket.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI ticketingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ticketing System API")
                        .description("Etkinlik, koltuk ve bilet yönetimi")
                        .version("1.0.0"));
    }

    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("ticketing")
                .pathsToMatch("/api/**")
                .build();
    }
}
