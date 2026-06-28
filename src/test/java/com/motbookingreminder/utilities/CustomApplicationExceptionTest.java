package com.motbookingreminder.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;

class CustomApplicationExceptionTest {

    @Test
    void constructor_setsMessageAndStatus() {
        CustomApplicationException ex = new CustomApplicationException("Resource not found", HttpStatus.NOT_FOUND);
        assertThat(ex.getMessage()).isEqualTo("Resource not found");
        assertThat(ex.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void isInstanceOfRuntimeException() {
        CustomApplicationException ex = new CustomApplicationException("error", HttpStatus.BAD_REQUEST);
        assertThat(ex).isInstanceOf(RuntimeException.class);
    }

    @Test
    void getStatus_returnsServiceUnavailable() {
        CustomApplicationException ex = new CustomApplicationException("Service down", HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(ex.getStatus()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
