package com.motbookingreminder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.utilities.ApiErrorResponse;
import com.utilities.CustomApplicationException;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.google.gson.Gson;

@Service
public class VehicleService {

    @Value("${api.key}")
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
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            String errorBody = e.getResponseBodyAsString();

            Gson gson = new Gson();
            ApiErrorResponse apiError = gson.fromJson(errorBody, ApiErrorResponse.class);

            // Optionally log the error or handle it based on the status code

            throw new CustomApplicationException(apiError.getMessage(), e.getStatusCode());
        } catch (RestClientException e) {
            throw new CustomApplicationException("A connectivity issue occurred", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
