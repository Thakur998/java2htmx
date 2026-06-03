package com.j2htmx.auto.registry;


import com.j2htmx.auto.annotations.Node;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class NodeRegistry {

    private final ApplicationContext applicationContext;

    public NodeRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Map<String, Object> getNodes() {
        return applicationContext.getBeansWithAnnotation(Node.class);
    }

    public <T> T getNode(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}