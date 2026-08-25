package com.ivan.logisticsapi.controller;

import com.ivan.logisticsapi.model.Vehicle;
import com.ivan.logisticsapi.service.VehicleService;
import jakarta.validation.Valid;
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
    public Vehicle addVehicle(@Valid @RequestBody Vehicle vehicle){
        return vehicleService.addVehicle(vehicle);
    }
    @GetMapping
    public List<Vehicle> getVehicles(){
        return vehicleService.getVehicles();
    }
    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id){
        return vehicleService.findVehicleById(id);
    }

    @PutMapping("/{id}")
    public Vehicle  updateVehicle(
            @PathVariable Long id,
            @Valid @RequestBody Vehicle vehicle){
        Vehicle tempVehicle = vehicleService.findVehicleById(id);

        tempVehicle.setBrand(vehicle.getBrand());
        tempVehicle.setCapacity(vehicle.getCapacity());
        tempVehicle.setModel(vehicle.getModel());
        tempVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());

        return vehicleService.addVehicle(tempVehicle);
    }
    @DeleteMapping("/{id}")
    public void deleteVehicleById(@PathVariable Long id){
        vehicleService.deleteVehicleById(id);
    }
}
