package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.model.Vehicle;
import com.ivan.logisticsapi.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle findVehicleById(Long id){
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.orElseThrow(NoSuchElementException::new);
    }

    public Vehicle addVehicle(Vehicle vehicle){
       return vehicleRepository.save(vehicle);
    }

    public void deleteVehicleById(Long id){
        findVehicleById(id);
        vehicleRepository.deleteById(id);
    }
}
