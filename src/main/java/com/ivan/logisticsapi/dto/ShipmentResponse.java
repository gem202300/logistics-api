package com.ivan.logisticsapi.dto;

public class ShipmentResponse {
    private Long id;
    private String trackingNumber;
    private String description;
    private String status;
    private Long clientId;
    private Long warehouseId;
    private Long vehicleId;

    public ShipmentResponse() {
    }
    public ShipmentResponse(Long id, String trackingNumber, String description, String status, Long clientId, Long warehouseId, Long vehicleId) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.description = description;
        this.status = status;
        this.clientId = clientId;
        this.warehouseId = warehouseId;
        this.vehicleId = vehicleId;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }
    public Long getClientId() {
        return clientId;
    }
    public Long getWarehouseId() {
        return warehouseId;
    }
    public Long getVehicleId() {
        return vehicleId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }
}
