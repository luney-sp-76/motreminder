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

    @PostMapping("/setreminder")
    public String setReminder() {
        return "setreminder";
    }
}
