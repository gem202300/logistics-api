package com.ivan.logisticsapi.controller;

import com.ivan.logisticsapi.dto.VehicleRequest;
import com.ivan.logisticsapi.dto.VehicleResponse;
import com.ivan.logisticsapi.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public VehicleResponse addVehicle(@Valid @RequestBody VehicleRequest vehicleRequest) {
        return vehicleService.addVehicle(vehicleRequest);
    }

    @GetMapping
    public List<VehicleResponse> getVehicles() {
        return vehicleService.getVehicles();
    }

    @GetMapping("/{id}")
    public VehicleResponse getVehicleById(@PathVariable Long id) {
        return vehicleService.findVehicleById(id);
    }

    @PutMapping("/{id}")
    public VehicleResponse updateVehicle(
            @PathVariable Long id,
            @Valid @RequestBody VehicleRequest vehicleRequest) {
        return vehicleService.updateVehicleById(id, vehicleRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Long id) {
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.noContent().build();
    }
}