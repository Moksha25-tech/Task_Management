package com.moksha.todof.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configure {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
