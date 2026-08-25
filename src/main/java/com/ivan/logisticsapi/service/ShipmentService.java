package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.model.Shipment;
import com.ivan.logisticsapi.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }
    public List<Shipment> getShipments(){
        return shipmentRepository.findAll();
    }

    public Shipment findShipmentById(Long id){
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        return shipment.orElseThrow(NoSuchElementException::new);
    }

    public Shipment addShipment(Shipment shipment){
        return shipmentRepository.save(shipment);
    }

    public void deleteShipmentById(Long id){
        findShipmentById(id);
        shipmentRepository.deleteById(id);
    }
    public Shipment findShipmentByTrackingNumber(String trackingNumber){
        Optional<Shipment> shipment = shipmentRepository.findByTrackingNumber(trackingNumber);
        return shipment.orElseThrow(NoSuchElementException::new);
    }
}
