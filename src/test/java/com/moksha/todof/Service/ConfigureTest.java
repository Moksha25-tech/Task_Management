package com.moksha.todof.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConfigureTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void restTemplateBeanShouldExist() {
        assertNotNull(restTemplate, "RestTemplate bean should be created and injected");
    }
}
