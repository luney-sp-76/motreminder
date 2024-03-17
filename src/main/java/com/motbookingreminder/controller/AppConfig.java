package com.motbookingreminder.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * This class represents the application configuration for the MOT Booking
 * Reminder application.
 * It provides a bean definition for the RestTemplate used for making RESTful
 * API calls.
 */
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}