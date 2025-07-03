package com.moksha.todof.Service;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class ConfigureTest {

    @Test
    void testRestTemplateBeanCreation() {
        // Load the Spring context manually
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configure.class);

        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        assertNotNull(restTemplate, "RestTemplate bean should not be null");

        context.close();
    }
}
