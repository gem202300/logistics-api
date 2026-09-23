package com.ivan.logisticsapi.dto;

import com.ivan.logisticsapi.enums.ShipmentStatus;
import jakarta.validation.constraints.NotNull;

public class ShipmentStatusRequest {
    @NotNull
    private ShipmentStatus status;
    public ShipmentStatusRequest() {}


    public ShipmentStatus getStatus() {
        return status;
    }
    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }
}
