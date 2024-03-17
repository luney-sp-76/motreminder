package com.motbookingreminder.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.motbookingreminder.model.Greeting;

/**
 * Handles requests for the "/greeting" endpoint and returns a greeting message.
 *
 * @param name the name to include in the greeting message (default value is
 *             "Paul" if not provided)
 * @return a Greeting object containing the counter value and the formatted
 *         greeting message
 */
@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Paul") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
