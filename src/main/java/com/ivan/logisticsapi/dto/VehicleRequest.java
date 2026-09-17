package com.ivan.logisticsapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class VehicleRequest {
    @NotBlank
    private String registrationNumber;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @Positive
    private int capacity;
    public VehicleRequest() {}
    public VehicleRequest(String registrationNumber, String brand, String model, int capacity) {
        this.registrationNumber = registrationNumber;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
