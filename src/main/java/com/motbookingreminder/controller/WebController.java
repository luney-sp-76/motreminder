package com.motbookingreminder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/motcheck")
    public String showForm() {
        return "numberPlate";
    }

    @GetMapping("/setreminder")
    public String showReminderForm() {
        return "setreminder";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

}
