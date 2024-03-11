package com.motbookingreminder.model;

import com.google.gson.Gson;

public class GsonCar {
    private final Gson gson;

    public GsonCar() {
        this.gson = new Gson();
    }

    public Car convertJsonToCar(String json) {
        return gson.fromJson(json, Car.class);
    }

}
