package com.motbookingreminder.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;

class VehicleTest {

    @Test
    void defaultConstructor_createsVehicleWithNullFields() {
        Vehicle vehicle = new Vehicle();

        assertThat(vehicle.getRegistrationNumber()).isNull();
        assertThat(vehicle.getMake()).isNull();
        assertThat(vehicle.getMotStatus()).isNull();
        assertThat(vehicle.getCo2Emissions()).isZero();
    }

    @Test
    void allArgsConstructor_setsAllFields() {
        Date artEndDate = new Date(1000L);
        Date taxDueDate = new Date(2000L);
        Date motExpiryDate = new Date(3000L);
        Date dateOfLastV5CIssued = new Date(4000L);

        Vehicle vehicle = new Vehicle(
                artEndDate, 120, "RED", 1600, "PETROL", "FORD",
                false, "2018-01", "Valid", "AB12CDE",
                0, taxDueDate, "Taxed", motExpiryDate, "M1", "2 AXLE RIGID BODY",
                2018, "EURO 6", "1", dateOfLastV5CIssued);

        assertThat(vehicle.getArtEndDate()).isEqualTo(artEndDate);
        assertThat(vehicle.getCo2Emissions()).isEqualTo(120);
        assertThat(vehicle.getColour()).isEqualTo("RED");
        assertThat(vehicle.getEngineCapacity()).isEqualTo(1600);
        assertThat(vehicle.getFuelType()).isEqualTo("PETROL");
        assertThat(vehicle.getMake()).isEqualTo("FORD");
        assertThat(vehicle.isMarkedForExport()).isFalse();
        assertThat(vehicle.getMonthOfFirstRegistration()).isEqualTo("2018-01");
        assertThat(vehicle.getMotStatus()).isEqualTo("Valid");
        assertThat(vehicle.getRegistrationNumber()).isEqualTo("AB12CDE");
        assertThat(vehicle.getRevenueWeight()).isZero();
        assertThat(vehicle.getTaxDueDate()).isEqualTo(taxDueDate);
        assertThat(vehicle.getTaxStatus()).isEqualTo("Taxed");
        assertThat(vehicle.getMotExpiryDate()).isEqualTo(motExpiryDate);
        assertThat(vehicle.getTypeApproval()).isEqualTo("M1");
        assertThat(vehicle.getWheelplan()).isEqualTo("2 AXLE RIGID BODY");
        assertThat(vehicle.getYearOfManufacture()).isEqualTo(2018);
        assertThat(vehicle.getEuroStatus()).isEqualTo("EURO 6");
        assertThat(vehicle.getRealDrivingEmissions()).isEqualTo("1");
        assertThat(vehicle.getDateOfLastV5CIssued()).isEqualTo(dateOfLastV5CIssued);
    }

    @Test
    void settersAndGetters_updateFieldsCorrectly() {
        Vehicle vehicle = new Vehicle();
        Date date = new Date(5000L);

        vehicle.setRegistrationNumber("XY99ZZZ");
        vehicle.setMake("TOYOTA");
        vehicle.setMotStatus("Valid");
        vehicle.setColour("BLUE");
        vehicle.setCo2Emissions(110);
        vehicle.setEngineCapacity(1200);
        vehicle.setFuelType("HYBRID");
        vehicle.setMarkedForExport(true);
        vehicle.setYearOfManufacture(2020);
        vehicle.setTaxStatus("Taxed");
        vehicle.setEuroStatus("EURO 6");
        vehicle.setRealDrivingEmissions("2");
        vehicle.setWheelplan("2 AXLE RIGID BODY");
        vehicle.setTypeApproval("M1");
        vehicle.setMonthOfFirstRegistration("2020-06");
        vehicle.setRevenueWeight(1500);
        vehicle.setMotExpiryDate(date);
        vehicle.setTaxDueDate(date);
        vehicle.setArtEndDate(date);
        vehicle.setDateOfLastV5CIssued(date);

        assertThat(vehicle.getRegistrationNumber()).isEqualTo("XY99ZZZ");
        assertThat(vehicle.getMake()).isEqualTo("TOYOTA");
        assertThat(vehicle.getMotStatus()).isEqualTo("Valid");
        assertThat(vehicle.getColour()).isEqualTo("BLUE");
        assertThat(vehicle.getCo2Emissions()).isEqualTo(110);
        assertThat(vehicle.getEngineCapacity()).isEqualTo(1200);
        assertThat(vehicle.getFuelType()).isEqualTo("HYBRID");
        assertThat(vehicle.isMarkedForExport()).isTrue();
        assertThat(vehicle.getYearOfManufacture()).isEqualTo(2020);
        assertThat(vehicle.getTaxStatus()).isEqualTo("Taxed");
        assertThat(vehicle.getEuroStatus()).isEqualTo("EURO 6");
        assertThat(vehicle.getRealDrivingEmissions()).isEqualTo("2");
        assertThat(vehicle.getWheelplan()).isEqualTo("2 AXLE RIGID BODY");
        assertThat(vehicle.getTypeApproval()).isEqualTo("M1");
        assertThat(vehicle.getMonthOfFirstRegistration()).isEqualTo("2020-06");
        assertThat(vehicle.getRevenueWeight()).isEqualTo(1500);
        assertThat(vehicle.getMotExpiryDate()).isEqualTo(date);
        assertThat(vehicle.getTaxDueDate()).isEqualTo(date);
        assertThat(vehicle.getArtEndDate()).isEqualTo(date);
        assertThat(vehicle.getDateOfLastV5CIssued()).isEqualTo(date);
    }
}
