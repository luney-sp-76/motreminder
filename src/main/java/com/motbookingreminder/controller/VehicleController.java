package com.motbookingreminder.controller;

import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.motbookingreminder.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Adjust the pattern to match your date format

    @PostMapping("/vehicles")
    public String getVehicleDetails(@RequestParam("registrationNumber") String registrationNumber, Model model) {
        String jsonResponse = vehicleService.getVehicleDetails(registrationNumber);
        Car car = new Gson().fromJson(jsonResponse, Car.class);

        Date motExpiryDate = car.getMotExpiryDate(); // Assuming this returns a java.util.Date
        LocalDate localMotExpiryDate = motExpiryDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // convert and compare the date
        LocalDate today = LocalDate.now();
        String placeholderMessage;

        if (car.getMotStatus().equals("Invalid")) {
            placeholderMessage = "Book your MOT today";
        } else if (localMotExpiryDate.isBefore(today.plusMonths(3))) {
            placeholderMessage = "Book your MOT today";
        } else if (localMotExpiryDate.isAfter(today.plusMonths(3))) {
            placeholderMessage = "Set a Reminder";
        } else {
            placeholderMessage = "Check your MOT status"; // Default message or any other logic you'd like to implement
        }

        // Assuming Car class has a method getMotExpiryDate() that returns the MOT date
        // as
        // String
        model.addAttribute("motDate", car.getMotExpiryDate());
        model.addAttribute("motStatus", car.getMotStatus());
        model.addAttribute("taxDate", car.getTaxDueDate());
        model.addAttribute("carMake", car.getMake());
        model.addAttribute("carClass", car.getClass());
        model.addAttribute("carYear", car.getYearOfManufacture());
        model.addAttribute("carFuel", car.getFuelType());
        model.addAttribute("carColour", car.getColour());
        model.addAttribute("carEngineSize", car.getEngineCapacity());
        model.addAttribute("placeholderMessage", placeholderMessage);

        return "vehicleInfo"; // Name of the HTML file to display the MOT date
    }
}
