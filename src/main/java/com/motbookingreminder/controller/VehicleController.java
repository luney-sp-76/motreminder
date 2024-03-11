package com.motbookingreminder.controller;

import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.motbookingreminder.model.Car;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    private final Gson gson;

    public VehicleController(VehicleService vehicleService, Gson gson) {
        this.vehicleService = vehicleService;
        this.gson = gson;
    }

    @PostMapping
    public Car getVehicleDetails(@RequestBody String registrationNumber) {
        String jsonResponse = vehicleService.getVehicleDetails(registrationNumber);
        return gson.fromJson(jsonResponse, Car.class);
    }
}
