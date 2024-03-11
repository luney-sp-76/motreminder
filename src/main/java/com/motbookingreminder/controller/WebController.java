package com.motbookingreminder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/motcheck")
    public String showForm() {
        return "numberPlate";
    }

}
