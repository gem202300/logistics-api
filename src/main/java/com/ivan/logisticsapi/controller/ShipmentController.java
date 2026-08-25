package com.ivan.logisticsapi.controller;

import com.ivan.logisticsapi.model.Shipment;
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
    public Shipment addShipment(@Valid @RequestBody Shipment shipment){
        return shipmentService.addShipment(shipment);
    }
    @GetMapping
    public List<Shipment> getShipments(){
        return shipmentService.getShipments();
    }
    @GetMapping("/{id}")
    public Shipment getShipmentById(@PathVariable Long id){
        return shipmentService.findShipmentById(id);
    }

    @PutMapping("/{id}")
    public Shipment  updateShipment(
            @PathVariable Long id,
            @Valid @RequestBody Shipment shipment) {
        Shipment tempShipment = shipmentService.findShipmentById(id);
        tempShipment.setDescription(shipment.getDescription());
        tempShipment.setStatus(shipment.getStatus());
        tempShipment.setTrackingNumber(shipment.getTrackingNumber());
        tempShipment.setWarehouse(shipment.getWarehouse());
        tempShipment.setClient(shipment.getClient());
        tempShipment.setVehicle(shipment.getVehicle());
        return shipmentService.addShipment(tempShipment);
    }
    @DeleteMapping("/{id}")
    public void deleteShipmentById(@PathVariable Long id){
        shipmentService.deleteShipmentById(id);
    }
    @GetMapping("/tracking/{trackingNumber}")
    public Shipment getShipmentByTrackingNumber(@PathVariable String trackingNumber){
        return shipmentService.findShipmentByTrackingNumber(trackingNumber);
    }

}
