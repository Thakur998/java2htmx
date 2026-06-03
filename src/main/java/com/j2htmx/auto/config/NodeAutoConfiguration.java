package com.j2htmx.auto.config;

import com.j2htmx.auto.registry.NodeRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NodeAutoConfiguration {

    @Bean
    public NodeRegistry nodeRegistry(ApplicationContext context) {
        return new NodeRegistry(context);
    }
}