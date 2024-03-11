package com.motbookingreminder.controller;

import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.motbookingreminder.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicles")
    public String getVehicleDetails(@RequestParam("registrationNumber") String registrationNumber, Model model) {
        String jsonResponse = vehicleService.getVehicleDetails(registrationNumber);
        Car car = new Gson().fromJson(jsonResponse, Car.class);

        // Assuming Car class has a method getMotExpiryDate() that returns the MOT date
        // as
        // String
        model.addAttribute("motDate", car.getMotExpiryDate());
        model.addAttribute("taxDate", car.getTaxDueDate());
        model.addAttribute("carMake", car.getMake());
        model.addAttribute("carClass", car.getClass());
        model.addAttribute("carYear", car.getYearOfManufacture());
        model.addAttribute("carFuel", car.getFuelType());
        model.addAttribute("carColour", car.getColour());
        model.addAttribute("carEngineSize", car.getEngineCapacity());

        return "vehicleInfo"; // Name of the HTML file to display the MOT date
    }
}
