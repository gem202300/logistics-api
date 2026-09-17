package com.ivan.logisticsapi.dto;

import jakarta.persistence.GeneratedValue;

public class VehicleResponse {
    private Long id;

    private String registrationNumber;

    private String brand;

    private String model;

    private int capacity;

    public VehicleResponse() {}

    public VehicleResponse(Long id, String registrationNumber, String brand, String model, int capacity) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
    }
    public Long getId() {
        return id;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public int getCapacity() {
        return capacity;
    }

}
