package com.motbookingreminder.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class GsonVehicleTest {

    @Test
    void convertJsonToVehicle_validJson_returnsVehicleWithCorrectFields() {
        GsonVehicle gsonVehicle = new GsonVehicle();
        String json = "{" +
            "\"registrationNumber\":\"AB12CDE\"," +
            "\"make\":\"FORD\"," +
            "\"colour\":\"RED\"," +
            "\"fuelType\":\"PETROL\"," +
            "\"yearOfManufacture\":2018," +
            "\"motStatus\":\"Valid\"}";

        Vehicle vehicle = gsonVehicle.convertJsonToVehicle(json);

        assertThat(vehicle.getRegistrationNumber()).isEqualTo("AB12CDE");
        assertThat(vehicle.getMake()).isEqualTo("FORD");
        assertThat(vehicle.getColour()).isEqualTo("RED");
        assertThat(vehicle.getFuelType()).isEqualTo("PETROL");
        assertThat(vehicle.getYearOfManufacture()).isEqualTo(2018);
        assertThat(vehicle.getMotStatus()).isEqualTo("Valid");
    }

    @Test
    void convertJsonToVehicle_partialJson_returnsVehicleWithNullsForMissingFields() {
        GsonVehicle gsonVehicle = new GsonVehicle();
        String json = "{\"registrationNumber\":\"XY99ZZZ\"}";

        Vehicle vehicle = gsonVehicle.convertJsonToVehicle(json);

        assertThat(vehicle.getRegistrationNumber()).isEqualTo("XY99ZZZ");
        assertThat(vehicle.getMake()).isNull();
        assertThat(vehicle.getColour()).isNull();
    }

    @Test
    void convertJsonToVehicle_emptyJson_returnsVehicleWithAllNullFields() {
        GsonVehicle gsonVehicle = new GsonVehicle();

        Vehicle vehicle = gsonVehicle.convertJsonToVehicle("{}");

        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getRegistrationNumber()).isNull();
        assertThat(vehicle.getMake()).isNull();
    }
}
