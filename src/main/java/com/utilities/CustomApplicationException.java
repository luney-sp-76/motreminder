package com.utilities;

import org.springframework.http.HttpStatusCode;

public class CustomApplicationException extends RuntimeException {
    private HttpStatusCode status; // Store the HTTP status code for the exception

    // Constructor that takes a message and an HttpStatus
    public CustomApplicationException(String message, HttpStatusCode status) {
        super(message); // Pass the message to the superclass constructor
        this.status = status; // Set the HTTP status code
    }

    // Getter for the HttpStatus code
    public HttpStatusCode getStatus() {
        return this.status;
    }
}
