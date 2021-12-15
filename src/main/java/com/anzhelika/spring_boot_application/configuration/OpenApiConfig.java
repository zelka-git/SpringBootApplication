package com.anzhelika.spring_boot_application.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {
    private final BuildProperties buildProperties;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Application API")
                .version(buildProperties.getVersion())
                .description("This is a simple Application server created using springdocs - a library for OpenAPI 3" +
                        " with spring boot."));
    }
}
