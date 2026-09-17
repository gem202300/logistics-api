package com.ivan.logisticsapi.controller;

import com.ivan.logisticsapi.dto.ShipmentRequest;
import com.ivan.logisticsapi.dto.ShipmentResponse;
import com.ivan.logisticsapi.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ShipmentResponse addShipment(
            @Valid @RequestBody ShipmentRequest shipmentRequest
    ) {
        return shipmentService.addShipment(shipmentRequest);
    }

    @GetMapping
    public List<ShipmentResponse> getShipments() {
        return shipmentService.getShipments();
    }

    @GetMapping("/{id}")
    public ShipmentResponse getShipmentById(
            @PathVariable Long id
    ) {
        return shipmentService.findShipmentById(id);
    }

    @PutMapping("/{id}")
    public ShipmentResponse updateShipment(
            @PathVariable Long id,
            @Valid @RequestBody ShipmentRequest shipmentRequest
    ) {
        return shipmentService.updateShipmentById(id, shipmentRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteShipmentById(@PathVariable Long id) {
        shipmentService.deleteShipmentById(id);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ShipmentResponse getShipmentByTrackingNumber(
            @PathVariable String trackingNumber
    ) {
        return shipmentService.findShipmentByTrackingNumber(trackingNumber);
    }
}