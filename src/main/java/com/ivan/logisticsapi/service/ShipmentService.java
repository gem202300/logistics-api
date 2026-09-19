package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.dto.ShipmentRequest;
import com.ivan.logisticsapi.dto.ShipmentResponse;
import com.ivan.logisticsapi.enums.ShipmentStatus;
import com.ivan.logisticsapi.model.Client;
import com.ivan.logisticsapi.model.Shipment;
import com.ivan.logisticsapi.model.Vehicle;
import com.ivan.logisticsapi.model.Warehouse;
import com.ivan.logisticsapi.repository.ClientRepository;
import com.ivan.logisticsapi.repository.ShipmentRepository;
import com.ivan.logisticsapi.repository.VehicleRepository;
import com.ivan.logisticsapi.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ClientRepository clientRepository;
    private final WarehouseRepository warehouseRepository;
    private final VehicleRepository vehicleRepository;

    public ShipmentService(
            ShipmentRepository shipmentRepository,
            ClientRepository clientRepository,
            WarehouseRepository warehouseRepository,
            VehicleRepository vehicleRepository
    ) {
        this.shipmentRepository = shipmentRepository;
        this.clientRepository = clientRepository;
        this.warehouseRepository = warehouseRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<ShipmentResponse> getShipments() {
        return shipmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ShipmentResponse findShipmentById(Long id) {
        Shipment shipment = getShipmentEntityById(id);
        return toResponse(shipment);
    }

    public ShipmentResponse addShipment(ShipmentRequest shipmentRequest) {

        Client client = clientRepository.findById(shipmentRequest.getClientId())
                .orElseThrow(()-> new NoSuchElementException("Client with id " + shipmentRequest.getClientId() + " not found"));

        Warehouse warehouse = warehouseRepository.findById(shipmentRequest.getWarehouseId())
                .orElseThrow(()-> new NoSuchElementException("Warehouse not found with id: "+ shipmentRequest.getWarehouseId()));

        Vehicle vehicle = vehicleRepository.findById(shipmentRequest.getVehicleId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id: "+ shipmentRequest.getVehicleId()));

        Shipment shipment = new Shipment(
                shipmentRequest.getTrackingNumber(),
                shipmentRequest.getDescription(),
                ShipmentStatus.CREATED,
                client,
                warehouse,
                vehicle
        );

        Shipment savedShipment = shipmentRepository.save(shipment);

        return toResponse(savedShipment);
    }

    public ShipmentResponse updateShipmentById(
            Long id,
            ShipmentRequest shipmentRequest
    ) {
        Shipment shipment = getShipmentEntityById(id);

        Client client = clientRepository.findById(shipmentRequest.getClientId())
                .orElseThrow(() -> new NoSuchElementException("Client not found  with id: "+ shipmentRequest.getClientId()));

        Warehouse warehouse = warehouseRepository.findById(shipmentRequest.getWarehouseId())
                .orElseThrow(()-> new NoSuchElementException("Warehouse not found  with id: "+ shipmentRequest.getWarehouseId()));

        Vehicle vehicle = vehicleRepository.findById(shipmentRequest.getVehicleId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found  with id: "+ shipmentRequest.getVehicleId()));

        shipment.setTrackingNumber(shipmentRequest.getTrackingNumber());
        shipment.setDescription(shipmentRequest.getDescription());
        shipment.setClient(client);
        shipment.setWarehouse(warehouse);
        shipment.setVehicle(vehicle);

        Shipment savedShipment = shipmentRepository.save(shipment);

        return toResponse(savedShipment);
    }

    public void deleteShipmentById(Long id) {
        getShipmentEntityById(id);
        shipmentRepository.deleteById(id);
    }

    public ShipmentResponse findShipmentByTrackingNumber(String trackingNumber) {
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(()-> new NoSuchElementException("Shipment not found with tracking number: "+ trackingNumber) );

        return toResponse(shipment);
    }

    private Shipment getShipmentEntityById(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Shipment not found with id: "+ id));
    }
    public ShipmentResponse updateStatus(Long id, ShipmentStatus newStatus) {
        Shipment shipment = getShipmentEntityById(id);
        ShipmentStatus current = shipment.getStatus();

        if (current == ShipmentStatus.CREATED) {
             if (newStatus != ShipmentStatus.IN_TRANSIT && newStatus != ShipmentStatus.CANCELLED) {
                throw new IllegalStateException("Cannot change status from CREATED to " + newStatus);
            }
        }
        else if (current == ShipmentStatus.IN_TRANSIT) {
           if (newStatus != ShipmentStatus.DELIVERED && newStatus != ShipmentStatus.CANCELLED) {
                throw new IllegalStateException("Cannot change status from IN_TRANSIT to " + newStatus);
            }
        }
        else {
            throw new IllegalStateException("Cannot change status from " + current);
        }

        shipment.setStatus(newStatus);
        shipmentRepository.save(shipment);
        return toResponse(shipment);
    }

    private ShipmentResponse toResponse(Shipment shipment) {
        return new ShipmentResponse(
                shipment.getId(),
                shipment.getTrackingNumber(),
                shipment.getDescription(),
                shipment.getStatus(),
                shipment.getClient().getId(),
                shipment.getWarehouse().getId(),
                shipment.getVehicle().getId()
        );
    }
}