package com.motbookingreminder.controller;

import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.motbookingreminder.model.Car;
import com.motbookingreminder.utilities.CustomApplicationException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * The VehicleController class is responsible for handling HTTP requests related
 * to vehicles.
 * It interacts with the VehicleService to retrieve vehicle details and perform
 * necessary operations.
 */
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
        try {
            String jsonResponse = vehicleService.getVehicleDetails(registrationNumber);

            if (jsonResponse.equals(
                    "Please check the registration number and try again. If the problem persists, contact support.")) {
                model.addAttribute("errorMessage", jsonResponse);
                return "numberPlate";
            }

            Car car = new Gson().fromJson(jsonResponse, Car.class);

            Date motExpiryDate = car.getMotExpiryDate(); // Assuming this returns a java.util.Date
            LocalDate localMotExpiryDate = motExpiryDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            Date taxDueDate = car.getTaxDueDate();
            LocalDate localTaxDueDate = LocalDate.now(); // Assuming this returns a java.util.Date
            if (taxDueDate == null) {
                localTaxDueDate = null;

            } else {
                localTaxDueDate = taxDueDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            }

            // convert and compare the date
            LocalDate today = LocalDate.now();
            String placeholderMessage;

            LocalDate reminderDate = null;

            if (car.getMotStatus().equals("Invalid")) {
                placeholderMessage = "Book your MOT today";
                reminderDate = today;
            } else if (localMotExpiryDate.isBefore(today.plusMonths(3))) {
                placeholderMessage = "Book your MOT today";
                reminderDate = today;
            } else if (localMotExpiryDate.isAfter(today.plusMonths(3))) {
                placeholderMessage = "Set a Reminder";
                reminderDate = localMotExpiryDate.minusMonths(3);
            } else {
                placeholderMessage = "Check your MOT status"; // Default message or any other logic you'd like to //
                                                              // implement
            }

            // Assuming Car class has a method getMotExpiryDate() that returns the MOT date
            // as
            // String
            model.addAttribute("motDate", localMotExpiryDate);
            model.addAttribute("motStatus", car.getMotStatus());
            model.addAttribute("taxDate", localTaxDueDate);
            model.addAttribute("carMake", car.getMake());
            model.addAttribute("carClass", car.getClass());
            model.addAttribute("carYear", car.getYearOfManufacture());
            model.addAttribute("carFuel", car.getFuelType());
            model.addAttribute("carColour", car.getColour());
            model.addAttribute("carEngineSize", car.getEngineCapacity());
            model.addAttribute("registrationNumber", car.getRegistrationNumber());
            model.addAttribute("placeholderMessage", placeholderMessage);
            // Only add reminderDate to the model if it's set
            if (reminderDate != null) {
                model.addAttribute("reminderDate", reminderDate);
            }

            return "vehicleInfo"; // Name of the HTML file to display the MOT date
        } catch (CustomApplicationException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "numberPlate"; // show the form page again with the error message
        }
    }
}
