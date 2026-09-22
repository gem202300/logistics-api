package com.ivan.logisticsapi.config;

import com.ivan.logisticsapi.enums.ShipmentStatus;
import com.ivan.logisticsapi.model.Client;
import com.ivan.logisticsapi.model.Shipment;
import com.ivan.logisticsapi.model.Vehicle;
import com.ivan.logisticsapi.model.Warehouse;
import com.ivan.logisticsapi.repository.ClientRepository;
import com.ivan.logisticsapi.repository.ShipmentRepository;
import com.ivan.logisticsapi.repository.VehicleRepository;
import com.ivan.logisticsapi.repository.WarehouseRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final WarehouseRepository warehouseRepository;
    private final VehicleRepository vehicleRepository;
    private final ShipmentRepository shipmentRepository;

    private static final int CLIENTS_COUNT = 5;
    private static final int WAREHOUSES_COUNT = 3;
    private static final int VEHICLES_COUNT = 4;
    private static final int SHIPMENTS_COUNT = 12;

    public DataInitializer(
            ClientRepository clientRepository,
            WarehouseRepository warehouseRepository,
            VehicleRepository vehicleRepository,
            ShipmentRepository shipmentRepository
    ) {
        this.clientRepository = clientRepository;
        this.warehouseRepository = warehouseRepository;
        this.vehicleRepository = vehicleRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public void run(String... args) {
        if (clientRepository.count() > 0) {
            return;
        }

        Faker faker = new Faker();

        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < CLIENTS_COUNT; i++) {
            Client client = new Client(
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().phoneNumber(),
                    faker.address().fullAddress()
            );
            clients.add(clientRepository.save(client));
        }

        List<Warehouse> warehouses = new ArrayList<>();
        for (int i = 0; i < WAREHOUSES_COUNT; i++) {
            Warehouse warehouse = new Warehouse(
                    "Warehouse " + (i + 1),
                    faker.address().fullAddress(),
                    faker.address().city(),
                    faker.number().numberBetween(200, 2000)
            );
            warehouses.add(warehouseRepository.save(warehouse));
        }

        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < VEHICLES_COUNT; i++) {
            Vehicle vehicle = new Vehicle(
                    faker.bothify("??-####").toUpperCase(), // наприклад AB-1234
                    faker.vehicle().manufacturer(),
                    faker.vehicle().model(),
                    faker.number().numberBetween(500, 5000)
            );
            vehicles.add(vehicleRepository.save(vehicle));
        }

        ShipmentStatus[] statuses = ShipmentStatus.values();

        for (int i = 0; i < SHIPMENTS_COUNT; i++) {
            Client client = clients.get(ThreadLocalRandom.current().nextInt(clients.size()));
            Warehouse warehouse = warehouses.get(ThreadLocalRandom.current().nextInt(warehouses.size()));
            Vehicle vehicle = vehicles.get(ThreadLocalRandom.current().nextInt(vehicles.size()));

            ShipmentStatus status = statuses[ThreadLocalRandom.current().nextInt(statuses.length)];

            Shipment shipment = new Shipment(
                    faker.bothify("TRK-########").toUpperCase(),
                    faker.commerce().productName(),
                    status,
                    client,
                    warehouse,
                    vehicle
            );

            shipmentRepository.save(shipment);
        }

        System.out.println(">>> Test data created: "
                + CLIENTS_COUNT + " clients, "
                + WAREHOUSES_COUNT + " warehouses, "
                + VEHICLES_COUNT + " vehicles, "
                + SHIPMENTS_COUNT + " shipments");
    }
}