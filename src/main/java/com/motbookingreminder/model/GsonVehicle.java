package com.motbookingreminder.model;

import com.google.gson.Gson;

/**
 * The GsonCar class is responsible for converting JSON strings to Car objects
 * using Gson library.
 */
public class GsonVehicle {
    private final Gson gson;

    public GsonVehicle() {
        this.gson = new Gson();
    }

    /**
     * Converts a JSON string to a Vehicle object.
     *
     * @param json the JSON string to be converted
     * @return the Vehicle object created from the JSON string
     */
    public Vehicle convertJsonToVehicle(String json) {
        return gson.fromJson(json, Vehicle.class);
    }
}
