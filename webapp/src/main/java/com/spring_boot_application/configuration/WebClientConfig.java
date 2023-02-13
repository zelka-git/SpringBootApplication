package com.spring_boot_application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(@Value("${spring.codec.max-in-memory-size}") Integer codecMaxInMemorySize){
        return WebClient.builder()
                .codecs(configurator -> configurator.defaultCodecs().maxInMemorySize(codecMaxInMemorySize))
                .build();
    }
}
