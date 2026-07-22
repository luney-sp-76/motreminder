package com.motbookingreminder.controller;

import com.motbookingreminder.utilities.CustomApplicationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleControllerTest {

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    @Test
    void showVehiclesList_returnsVehiclesView() {
        String view = vehicleController.showVehiclesList();
        assertThat(view).isEqualTo("vehicles");
    }

    @Test
    void getVehicleDetails_customException_returnsNumberPlateViewWithErrorMessage() {
        when(vehicleService.getVehicleDetails("INVALID"))
                .thenThrow(new CustomApplicationException("Vehicle not found", HttpStatus.NOT_FOUND));
        Model model = new ExtendedModelMap();

        String view = vehicleController.getVehicleDetails("INVALID", model);

        assertThat(view).isEqualTo("numberPlate");
        assertThat(model.getAttribute("errorMessage")).isEqualTo("Vehicle not found");
    }

    @Test
    void showVehicleInfo_customException_returnsVehiclesViewWithErrorMessage() {
        when(vehicleService.getVehicleDetails("INVALID"))
                .thenThrow(new CustomApplicationException("Not found", HttpStatus.NOT_FOUND));
        Model model = new ExtendedModelMap();

        String view = vehicleController.showVehicleInfo("INVALID", model);

        assertThat(view).isEqualTo("vehicles");
        assertThat(model.getAttribute("errorMessage")).isEqualTo("Not found");
    }

    @Test
    void getVehicleDetails_validVehicleJson_returnsVehicleInfoView() {
        // Vehicle JSON with motStatus set and no dates (exercises null-date path)
        String json = "{\"registrationNumber\":\"AB12CDE\",\"make\":\"FORD\",\"motStatus\":\"Valid\",\"colour\":\"RED\",\"fuelType\":\"PETROL\",\"yearOfManufacture\":2018,\"co2Emissions\":120,\"engineCapacity\":1600}";
        when(vehicleService.getVehicleDetails("AB12CDE")).thenReturn(json);
        Model model = new ExtendedModelMap();

        String view = vehicleController.getVehicleDetails("AB12CDE", model);

        assertThat(view).isEqualTo("vehicleInfo");
        assertThat(model.getAttribute("carMake")).isEqualTo("FORD");
        assertThat(model.getAttribute("motStatus")).isEqualTo("Valid");
        assertThat(model.getAttribute("carColour")).isEqualTo("RED");
        assertThat(model.getAttribute("carFuel")).isEqualTo("PETROL");
        assertThat(model.getAttribute("carYear")).isEqualTo(2018);
    }

    @Test
    void getVehicleDetails_invalidMotStatus_setsBookMotTodayPlaceholder() {
        // MotStatus=Invalid always triggers 'Book your MOT today'
        String json = "{\"registrationNumber\":\"AB12CDE\",\"make\":\"FORD\",\"motStatus\":\"Invalid\",\"colour\":\"RED\",\"fuelType\":\"PETROL\",\"yearOfManufacture\":2018}";
        when(vehicleService.getVehicleDetails("AB12CDE")).thenReturn(json);
        Model model = new ExtendedModelMap();

        vehicleController.getVehicleDetails("AB12CDE", model);

        assertThat(model.getAttribute("placeholderMessage")).isEqualTo("Book your MOT today");
    }
}
