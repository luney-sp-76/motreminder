package com.motbookingreminder.utilities;

/**
 * Represents an API error response.
 */
public class ApiErrorResponse {
    private String message;

    // Constructors, getters, and setters
    public ApiErrorResponse() {
    }

    /**
     * Gets the error message.
     * 
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     * 
     * @param message The error message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
