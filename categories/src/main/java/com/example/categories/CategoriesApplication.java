package com.example.categories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
public class CategoriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoriesApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
