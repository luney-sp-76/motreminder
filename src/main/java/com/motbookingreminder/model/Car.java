package com.motbookingreminder.model;

import java.util.Date;

public class Car {
    private Date artEndDate;
    private int co2Emissions;
    private String colour;
    private int engineCapacity;
    private String fuelType;
    private String make;
    private boolean markedForExport;
    private String monthOfFirstRegistration;
    private String motStatus;
    private String registrationNumber;
    private int revenueWeight;
    private Date taxDueDate;
    private String taxStatus;
    private Date motExpiryDate;
    private String typeApproval;
    private String wheelplan;
    private int yearOfManufacture;
    private String euroStatus;
    private String realDrivingEmissions;
    private Date dateOfLastV5CIssued;

    public Car(Date artEndDate, int co2Emissions, String colour, int engineCapacity, String fuelType, String make,
            boolean markedForExport, String monthOfFirstRegistration, String motStatus, String registrationNumber,
            int revenueWeight, Date taxDueDate, String taxStatus, Date motExpiryDate, String typeApproval,
            String wheelplan,
            int yearOfManufacture, String euroStatus, String realDrivingEmissions, Date dateOfLastV5CIssued) {
        this.artEndDate = artEndDate;
        this.co2Emissions = co2Emissions;
        this.colour = colour;
        this.engineCapacity = engineCapacity;
        this.fuelType = fuelType;
        this.make = make;
        this.markedForExport = markedForExport;
        this.monthOfFirstRegistration = monthOfFirstRegistration;
        this.motStatus = motStatus;
        this.registrationNumber = registrationNumber;
        this.revenueWeight = revenueWeight;
        this.taxDueDate = taxDueDate;
        this.taxStatus = taxStatus;
        this.motExpiryDate = motExpiryDate;
        this.typeApproval = typeApproval;
        this.wheelplan = wheelplan;
        this.yearOfManufacture = yearOfManufacture;
        this.euroStatus = euroStatus;
        this.realDrivingEmissions = realDrivingEmissions;
        this.dateOfLastV5CIssued = dateOfLastV5CIssued;
    }

    public Car() {
    }

    public Date getArtEndDate() {
        return artEndDate;
    }

    public void setArtEndDate(Date artEndDate) {
        this.artEndDate = artEndDate;
    }

    public int getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(int co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public boolean isMarkedForExport() {
        return markedForExport;
    }

    public void setMarkedForExport(boolean markedForExport) {
        this.markedForExport = markedForExport;
    }

    public String getMonthOfFirstRegistration() {
        return monthOfFirstRegistration;
    }

    public void setMonthOfFirstRegistration(String monthOfFirstRegistration) {
        this.monthOfFirstRegistration = monthOfFirstRegistration;
    }

    public String getMotStatus() {
        return motStatus;
    }

    public void setMotStatus(String motStatus) {
        this.motStatus = motStatus;
    }

    public Date getMotExpiryDate() {
        return motExpiryDate;
    }

    public void setMotExpiryDate(Date motExpiryDate) {
        this.motExpiryDate = motExpiryDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getRevenueWeight() {
        return revenueWeight;
    }

    public void setRevenueWeight(int revenueWeight) {
        this.revenueWeight = revenueWeight;
    }

    public Date getTaxDueDate() {
        return taxDueDate;
    }

    public void setTaxDueDate(Date taxDueDate) {
        this.taxDueDate = taxDueDate;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public String getTypeApproval() {
        return typeApproval;
    }

    public void setTypeApproval(String typeApproval) {
        this.typeApproval = typeApproval;
    }

    public String getWheelplan() {
        return wheelplan;
    }

    public void setWheelplan(String wheelplan) {
        this.wheelplan = wheelplan;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getEuroStatus() {
        return euroStatus;
    }

    public void setEuroStatus(String euroStatus) {
        this.euroStatus = euroStatus;
    }

    public String getRealDrivingEmissions() {
        return realDrivingEmissions;
    }

    public void setRealDrivingEmissions(String realDrivingEmissions) {
        this.realDrivingEmissions = realDrivingEmissions;
    }

    public Date getDateOfLastV5CIssued() {
        return dateOfLastV5CIssued;
    }

    public void setDateOfLastV5CIssued(Date dateOfLastV5CIssued) {
        this.dateOfLastV5CIssued = dateOfLastV5CIssued;
    }

}

// {
// "registrationNumber": "SH10MLZ",
// "taxStatus": "Taxed",
// "taxDueDate": "2024-09-01",
// "motStatus": "Valid",
// "make": "HYUNDAI",
// "yearOfManufacture": 2010,
// "engineCapacity": 1998,
// "co2Emissions": 177,
// "fuelType": "PETROL",
// "markedForExport": false,
// "colour": "GREY",
// "typeApproval": "M1",
// "revenueWeight": 1455,
// "dateOfLastV5CIssued": "2023-09-28",
// "motExpiryDate": "2024-04-15",
// "wheelplan": "2 AXLE RIGID BODY",
// "monthOfFirstRegistration": "2010-05"
// }
