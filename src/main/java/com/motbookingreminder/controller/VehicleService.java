package com.motbookingreminder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class VehicleService {

    @Value("${api.key}") // Assuming you have your API key in application.properties/yml
    private String apiKey;

    private final RestTemplate restTemplate;

    public VehicleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getVehicleDetails(String registrationNumber) {
        String url = "https://driver-vehicle-licensing.api.gov.uk/vehicle-enquiry/v1/vehicles";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-api-key", apiKey);

        String requestBody = String.format("{\"registrationNumber\":\"%s\"}", registrationNumber);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}
