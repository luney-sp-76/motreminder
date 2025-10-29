package com.motbookingreminder.controller;

import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.motbookingreminder.model.Vehicle;
import com.motbookingreminder.utilities.CustomApplicationException;
import java.time.format.DateTimeFormatter;
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

    /**
     * Display the vehicles list page
     */
    @GetMapping("/vehicles")
    public String showVehiclesList() {
        return "vehicles";
    }

    /**
     * Display specific vehicle information by registration number
     */
    @GetMapping("/vehicle/{regNumber}")
    public String showVehicleInfo(@PathVariable("regNumber") String regNumber, Model model) {
        try {
            String jsonResponse = vehicleService.getVehicleDetails(regNumber);

            if (jsonResponse.equals(
                    "Please check the registration number and try again. If the problem persists, contact support.")) {
                model.addAttribute("errorMessage", jsonResponse);
                return "vehicles"; // Redirect back to vehicles list
            }

            Vehicle car = new Gson().fromJson(jsonResponse, Vehicle.class);
            populateVehicleModel(car, model);
            model.addAttribute("selectedVehicle", regNumber);

            return "vehicleInfo";
        } catch (CustomApplicationException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "vehicles";
        }
    }

    @PostMapping("/vehicles")
    public String getVehicleDetails(@RequestParam("registrationNumber") String registrationNumber, Model model) {
        try {
            String jsonResponse = vehicleService.getVehicleDetails(registrationNumber);
            // Debug: Log the raw JSON response from the vehicle service
            System.out.println("Vehicle JSON response: " + jsonResponse);

            if (jsonResponse.equals(
                    "Please check the registration number and try again. If the problem persists, contact support.")) {
                model.addAttribute("errorMessage", jsonResponse);
                return "numberPlate";
            }

            Vehicle car = new Gson().fromJson(jsonResponse, Vehicle.class);
            populateVehicleModel(car, model);

            return "vehicleInfo"; // Name of the HTML file to display the MOT date
        } catch (CustomApplicationException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "numberPlate"; // show the form page again with the error message
        }
    }

    /**
     * Helper method to populate model with vehicle data
     */
    private void populateVehicleModel(Vehicle car, Model model) {
        // Assuming this returns a java.util.Date
        Date motExpiryDate = car.getMotExpiryDate();
        LocalDate localMotExpiryDate = LocalDate.now();

        if (motExpiryDate == null) {
            model.addAttribute("motErrorMessage", "There is no MOT date available for this vehicle");
        } else {
            localMotExpiryDate = motExpiryDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            model.addAttribute("motDate", localMotExpiryDate);
        }

        Date taxDueDate = car.getTaxDueDate();
        LocalDate localTaxDueDate = LocalDate.now();
        if (taxDueDate == null) {
            localTaxDueDate = null;
        } else {
            localTaxDueDate = taxDueDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            String formattedDate = localTaxDueDate.format(formatter);
            model.addAttribute("taxDate", formattedDate);
        }

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
            placeholderMessage = "Check your MOT status";
        }

        if (motExpiryDate == null) {
            model.addAttribute("motDate", "No MOT date available");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            String formattedDate = localMotExpiryDate.format(formatter);
            model.addAttribute("motDate", formattedDate);
            model.addAttribute("motDateIso", localMotExpiryDate.toString());
        }

        model.addAttribute("motStatus", car.getMotStatus());
        model.addAttribute("carMake", car.getMake());
        model.addAttribute("carClass", car.getClass());
        model.addAttribute("carYear", car.getYearOfManufacture());
        model.addAttribute("carFuel", car.getFuelType());
        model.addAttribute("carColour", car.getColour());
        model.addAttribute("carEngineSize", car.getEngineCapacity());
        model.addAttribute("registrationNumber", car.getRegistrationNumber());
        model.addAttribute("placeholderMessage", placeholderMessage);

        if (reminderDate != null) {
            model.addAttribute("reminderDate", reminderDate);
        }
    }
}
