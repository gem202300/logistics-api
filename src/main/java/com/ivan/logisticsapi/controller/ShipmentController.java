package com.ivan.logisticsapi.controller;

import com.ivan.logisticsapi.dto.ShipmentRequest;
import com.ivan.logisticsapi.dto.ShipmentResponse;
import com.ivan.logisticsapi.dto.ShipmentStatusRequest;
import com.ivan.logisticsapi.enums.ShipmentStatus;
import com.ivan.logisticsapi.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<ShipmentResponse> getShipments(@RequestParam(required = false) ShipmentStatus status, Pageable pageable) {
        return shipmentService.getShipments(status,pageable);
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
    public ResponseEntity<Void> deleteShipmentById(@PathVariable Long id) {
        shipmentService.deleteShipmentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ShipmentResponse getShipmentByTrackingNumber(
            @PathVariable String trackingNumber
    ) {
        return shipmentService.findShipmentByTrackingNumber(trackingNumber);
    }
    @PatchMapping("/{id}/status")
    public ShipmentResponse updateStatus(
            @PathVariable Long id,
            @RequestBody ShipmentStatusRequest request){
        return shipmentService.updateStatus(id, request.getStatus());
    }
}