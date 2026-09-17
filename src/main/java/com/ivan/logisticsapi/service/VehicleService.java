package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.dto.VehicleRequest;
import com.ivan.logisticsapi.dto.VehicleResponse;
import com.ivan.logisticsapi.model.Vehicle;
import com.ivan.logisticsapi.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleResponse> getVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public VehicleResponse findVehicleById(Long id) {
        Vehicle vehicle = getVehicleEntityById(id);
        return toResponse(vehicle);
    }

    public VehicleResponse addVehicle(VehicleRequest vehicleRequest) {
        Vehicle vehicle = new Vehicle(
                vehicleRequest.getRegistrationNumber(),
                vehicleRequest.getBrand(),
                vehicleRequest.getModel(),
                vehicleRequest.getCapacity()
        );

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return toResponse(savedVehicle);
    }

    public VehicleResponse updateVehicleById(Long id, VehicleRequest vehicleRequest) {
        Vehicle vehicle = getVehicleEntityById(id);

        vehicle.setRegistrationNumber(vehicleRequest.getRegistrationNumber());
        vehicle.setBrand(vehicleRequest.getBrand());
        vehicle.setModel(vehicleRequest.getModel());
        vehicle.setCapacity(vehicleRequest.getCapacity());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return toResponse(savedVehicle);
    }

    public void deleteVehicleById(Long id) {
        getVehicleEntityById(id);
        vehicleRepository.deleteById(id);
    }

    private Vehicle getVehicleEntityById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    private VehicleResponse toResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getRegistrationNumber(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getCapacity()
        );
    }
}