package com.ivan.logisticsapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ShipmentRequest {
    @NotBlank
    private String trackingNumber;
    @NotBlank
    private String description;
    @NotNull
    private Long clientId;
    @NotNull
    private Long warehouseId;
    @NotNull
    private Long vehicleId;

    public  ShipmentRequest() {
    }

    public ShipmentRequest(String trackingNumber, String description, Long clientId, Long warehouseId, Long vehicleId) {
        this.trackingNumber = trackingNumber;
        this.description = description;
        this.clientId = clientId;
        this.warehouseId = warehouseId;
        this.vehicleId = vehicleId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

}
