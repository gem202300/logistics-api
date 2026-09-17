package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.dto.ShipmentRequest;
import com.ivan.logisticsapi.dto.ShipmentResponse;
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
                .orElseThrow(NoSuchElementException::new);

        Warehouse warehouse = warehouseRepository.findById(shipmentRequest.getWarehouseId())
                .orElseThrow(NoSuchElementException::new);

        Vehicle vehicle = vehicleRepository.findById(shipmentRequest.getVehicleId())
                .orElseThrow(NoSuchElementException::new);

        Shipment shipment = new Shipment(
                shipmentRequest.getTrackingNumber(),
                shipmentRequest.getDescription(),
                shipmentRequest.getStatus(),
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
                .orElseThrow(NoSuchElementException::new);

        Warehouse warehouse = warehouseRepository.findById(shipmentRequest.getWarehouseId())
                .orElseThrow(NoSuchElementException::new);

        Vehicle vehicle = vehicleRepository.findById(shipmentRequest.getVehicleId())
                .orElseThrow(NoSuchElementException::new);

        shipment.setTrackingNumber(shipmentRequest.getTrackingNumber());
        shipment.setDescription(shipmentRequest.getDescription());
        shipment.setStatus(shipmentRequest.getStatus());
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
                .orElseThrow(NoSuchElementException::new);

        return toResponse(shipment);
    }

    private Shipment getShipmentEntityById(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
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