package com.motbookingreminder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This class is the controller for handling web requests related to MOT booking
 * reminders.
 * It contains methods for displaying various forms and handling the
 * corresponding actions.
 */
@Controller
public class WebController {

    @GetMapping("/motcheck")
    public String showForm() {
        return "numberPlate";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/setreminder")
    public String showReminderForm() {
        return "setreminder"; // Displays the form
    }

    @GetMapping("/setdate")
    public String setDate() {
        return "setdate"; // Displays the form
    }

    @GetMapping("/account")
    public String showAccount() {
        return "account"; // displays the account page
    }
}
