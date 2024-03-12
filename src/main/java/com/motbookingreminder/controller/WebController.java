package com.motbookingreminder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/setdate")
    public String setDate() {
        return "setdate"; // Displays the form
    }

}
