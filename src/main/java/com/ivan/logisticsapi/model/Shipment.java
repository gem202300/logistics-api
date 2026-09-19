package com.ivan.logisticsapi.model;

import com.ivan.logisticsapi.enums.ShipmentStatus;
import jakarta.persistence.*;

@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String trackingNumber;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Vehicle vehicle;

    public Shipment(
            String trackingNumber,
            String description,
            ShipmentStatus status,
            Client client,
            Warehouse warehouse,
            Vehicle vehicle
    ) {
        this.trackingNumber = trackingNumber;
        this.description = description;
        this.status = status;
        this.client = client;
        this.warehouse = warehouse;
        this.vehicle = vehicle;
    }

    public Shipment(){}

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

}
