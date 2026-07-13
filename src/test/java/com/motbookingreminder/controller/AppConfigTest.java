package com.motbookingreminder.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

class AppConfigTest {

    private final AppConfig appConfig = new AppConfig();

    @Test
    void restTemplate_returnsNonNullInstance() {
        RestTemplate restTemplate = appConfig.restTemplate();

        assertThat(restTemplate).isNotNull();
    }

    @Test
    void restTemplate_eachCallReturnsNewInstance() {
        RestTemplate first = appConfig.restTemplate();
        RestTemplate second = appConfig.restTemplate();

        // Factory method produces a fresh RestTemplate on each invocation
        assertThat(first).isNotSameAs(second);
    }
}
