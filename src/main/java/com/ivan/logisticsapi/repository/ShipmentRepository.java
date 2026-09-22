package com.ivan.logisticsapi.repository;

import com.ivan.logisticsapi.enums.ShipmentStatus;
import com.ivan.logisticsapi.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    boolean existsByClientId(Long clientId);

    boolean existsByWarehouseId(Long warehouseId);

    boolean existsByVehicleId(Long vehicleId);

    List<Shipment> findByStatus(ShipmentStatus status);

}
