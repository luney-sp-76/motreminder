package com.motbookingreminder.utilities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ApiErrorResponseTest {

    @Test
    void defaultConstructor_messageIsNull() {
        ApiErrorResponse response = new ApiErrorResponse();
        assertThat(response.getMessage()).isNull();
    }

    @Test
    void setMessage_thenGetMessage_returnsSetValue() {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setMessage("Vehicle not found");
        assertThat(response.getMessage()).isEqualTo("Vehicle not found");
    }

    @Test
    void setMessage_withNull_getMessageReturnsNull() {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setMessage("initial");
        response.setMessage(null);
        assertThat(response.getMessage()).isNull();
    }
}
