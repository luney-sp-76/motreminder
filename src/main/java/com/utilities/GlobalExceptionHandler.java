package com.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ModelAndView handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {
        ModelAndView mav = new ModelAndView();

        if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
            mav.addObject("errorMessage", "The request was invalid. Please check your input and try again.");
        } else if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            mav.addObject("errorMessage", "The requested resource was not found.");
        } else if (ex.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            mav.addObject("errorMessage", "An internal server error occurred. Please try again later.");
        } else if (ex.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
            mav.addObject("errorMessage", "The service is currently unavailable. Please try again later.");
        } else {
            // Generic error message for other HTTP errors
            mav.addObject("errorMessage", "An error occurred. Please try again later.");
        }

        mav.setStatus(ex.getStatusCode());
        mav.setViewName("errorPage"); // Set this to the name of your error view
        return mav;
    }

    @ExceptionHandler(CustomApplicationException.class)
    public ResponseEntity<Object> handleCustomException(CustomApplicationException ex) {
        // The CustomApplicationException includes the HttpStatus
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
