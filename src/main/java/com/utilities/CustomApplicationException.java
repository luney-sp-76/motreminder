package com.utilities;

import org.springframework.http.HttpStatusCode;

/**
 * CustomApplicationException is a custom exception class that extends
 * RuntimeException.
 * It is used to represent application-specific exceptions and includes an HTTP
 * status code.
 */
public class CustomApplicationException extends RuntimeException {
    private HttpStatusCode status; // Store the HTTP status code for the exception

    /**
     * Constructs a new CustomApplicationException with the specified message and
     * HTTP status code.
     *
     * @param message The detail message for the exception.
     * @param status  The HTTP status code associated with the exception.
     */
    public CustomApplicationException(String message, HttpStatusCode status) {
        super(message); // Pass the message to the superclass constructor
        this.status = status; // Set the HTTP status code
    }

    /**
     * Returns the HTTP status code associated with this exception.
     *
     * @return The HTTP status code.
     */
    public HttpStatusCode getStatus() {
        return this.status;
    }
}
