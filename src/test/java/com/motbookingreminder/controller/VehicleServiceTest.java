package com.motbookingreminder.controller;

import com.motbookingreminder.utilities.CustomApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehicleService = new VehicleService(restTemplate);
        ReflectionTestUtils.setField(vehicleService, "apiKey", "test-api-key");
    }

    @Test
    void getVehicleDetails_successfulResponse_returnsBody() {
        String expectedJson = "{\"registrationNumber\":\"AB12CDE\",\"make\":\"FORD\"}";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class)))
                .thenReturn(ResponseEntity.ok(expectedJson));

        String result = vehicleService.getVehicleDetails("AB12CDE");

        assertThat(result).isEqualTo(expectedJson);
    }

    @Test
    void getVehicleDetails_httpClientError_throwsCustomApplicationException() {
        String errorJson = "{\"message\":\"Vehicle not found\"}";
        HttpClientErrorException clientError = HttpClientErrorException.create(
                HttpStatus.NOT_FOUND, "Not Found",
                HttpHeaders.EMPTY,
                errorJson.getBytes(StandardCharsets.UTF_8),
                StandardCharsets.UTF_8);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class)))
                .thenThrow(clientError);

        assertThatThrownBy(() -> vehicleService.getVehicleDetails("ZZ99ABC"))
                .isInstanceOf(CustomApplicationException.class)
                .hasMessage("Vehicle not found")
                .satisfies(e -> assertThat(((CustomApplicationException) e).getStatus())
                        .isEqualTo(HttpStatus.NOT_FOUND));
    }

    @Test
    void getVehicleDetails_restClientException_throwsServiceUnavailableException() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class)))
                .thenThrow(new RestClientException("Connection refused"));

        assertThatThrownBy(() -> vehicleService.getVehicleDetails("AB12CDE"))
                .isInstanceOf(CustomApplicationException.class)
                .hasMessage("A connectivity issue occurred")
                .satisfies(e -> assertThat(((CustomApplicationException) e).getStatus())
                        .isEqualTo(HttpStatus.SERVICE_UNAVAILABLE));
    }
}
