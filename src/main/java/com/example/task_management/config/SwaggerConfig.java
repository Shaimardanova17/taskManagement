package com.example.task_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local development server")
                ))
                .info(new Info()
                        .title("Task Management API")
                        .version("1.0")
                        .description("API для управления задачами в системе task management")
                );
    }
}
