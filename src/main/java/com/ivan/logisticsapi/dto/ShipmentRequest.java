package com.ivan.logisticsapi.dto;

import jakarta.validation.constraints.NotBlank;

public class ShipmentRequest {
    @NotBlank
    private String trackingNumber;
    @NotBlank
    private String description;
    @NotBlank
    private String status;
    private Long clientId;
    private Long warehouseId;
    private Long vehicleId;
    public  ShipmentRequest() {
    }
    public ShipmentRequest(String trackingNumber, String description, String status, Long clientId, Long warehouseId, Long vehicleId) {
        this.trackingNumber = trackingNumber;
        this.description = description;
        this.status = status;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
