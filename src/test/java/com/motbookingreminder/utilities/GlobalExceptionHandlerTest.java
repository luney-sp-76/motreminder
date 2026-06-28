package com.motbookingreminder.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleHttpClientErrorException_badRequest_setsCorrectMessage() {
        HttpClientErrorException ex = HttpClientErrorException.create(
                HttpStatus.BAD_REQUEST, "Bad Request", HttpHeaders.EMPTY, null, StandardCharsets.UTF_8);

        ModelAndView result = handler.handleHttpClientErrorException(ex, webRequest);

        assertThat(result.getModel().get("errorMessage"))
                .isEqualTo("The request was invalid. Please check your input and try again.");
        assertThat(result.getViewName()).isEqualTo("errorPage");
    }

    @Test
    void handleHttpClientErrorException_notFound_setsCorrectMessage() {
        HttpClientErrorException ex = HttpClientErrorException.create(
                HttpStatus.NOT_FOUND, "Not Found", HttpHeaders.EMPTY, null, StandardCharsets.UTF_8);

        ModelAndView result = handler.handleHttpClientErrorException(ex, webRequest);

        assertThat(result.getModel().get("errorMessage"))
                .isEqualTo("The requested resource was not found.");
        assertThat(result.getViewName()).isEqualTo("errorPage");
    }

    @Test
    void handleHttpClientErrorException_serviceUnavailable_setsCorrectMessage() {
        HttpClientErrorException ex = HttpClientErrorException.create(
                HttpStatus.SERVICE_UNAVAILABLE, "Service Unavailable",
                HttpHeaders.EMPTY, null, StandardCharsets.UTF_8);

        ModelAndView result = handler.handleHttpClientErrorException(ex, webRequest);

        assertThat(result.getModel().get("errorMessage"))
                .isEqualTo("The service is currently unavailable. Please try again later.");
        assertThat(result.getViewName()).isEqualTo("errorPage");
    }

    @Test
    void handleHttpClientErrorException_otherStatus_setsGenericMessage() {
        HttpClientErrorException ex = HttpClientErrorException.create(
                HttpStatus.CONFLICT, "Conflict", HttpHeaders.EMPTY, null, StandardCharsets.UTF_8);

        ModelAndView result = handler.handleHttpClientErrorException(ex, webRequest);

        assertThat(result.getModel().get("errorMessage"))
                .isEqualTo("An error occurred. Please try again later.");
        assertThat(result.getViewName()).isEqualTo("errorPage");
    }

    @Test
    void handleCustomException_returnsResponseEntityWithMessageAndStatus() {
        CustomApplicationException ex = new CustomApplicationException("Vehicle not found", HttpStatus.NOT_FOUND);

        ResponseEntity<Object> result = handler.handleCustomException(ex);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isEqualTo("Vehicle not found");
    }
}
