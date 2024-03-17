package com.motbookingreminder.model;

import com.google.gson.Gson;

/**
 * The GsonCar class is responsible for converting JSON strings to Car objects
 * using Gson library.
 */
public class GsonCar {
    private final Gson gson;

    public GsonCar() {
        this.gson = new Gson();
    }

    /**
     * Converts a JSON string to a Car object.
     *
     * @param json the JSON string to be converted
     * @return the Car object created from the JSON string
     */
    public Car convertJsonToCar(String json) {
        return gson.fromJson(json, Car.class);
    }
}
