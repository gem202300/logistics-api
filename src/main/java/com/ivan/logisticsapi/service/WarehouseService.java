package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.dto.ClientResponse;
import com.ivan.logisticsapi.dto.WarehouseRequest;
import com.ivan.logisticsapi.dto.WarehouseResponse;
import com.ivan.logisticsapi.model.Client;
import com.ivan.logisticsapi.model.Warehouse;
import com.ivan.logisticsapi.repository.ShipmentRepository;
import com.ivan.logisticsapi.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ShipmentRepository shipmentRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, ShipmentRepository shipmentRepository) {
        this.warehouseRepository = warehouseRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public List<WarehouseResponse> getWarehouses(){
        return warehouseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
    private WarehouseResponse toResponse(Warehouse warehouse) {
        return new WarehouseResponse(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getAddress(),
                warehouse.getCity(),
                warehouse.getCapacity()
        );
    }
    public WarehouseResponse findWarehouseById(Long id){
        Warehouse warehouse = warehouseRepository
                .findById(id).orElseThrow(()-> new NoSuchElementException("Warehouse not found with id: "+ id));
        return toResponse(warehouse);
    }
    public WarehouseResponse addWarehouse(WarehouseRequest warehouseRequest){
        Warehouse warehouse = new Warehouse(
                warehouseRequest.getName(),
                warehouseRequest.getAddress(),
                warehouseRequest.getCity(),
                warehouseRequest.getCapacity()
        );
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
       return toResponse(savedWarehouse);
    }
    private Warehouse getWarehouseEntityById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Warehouse not found with id: "+ id));
    }

    public WarehouseResponse updateWarehouseById(Long id, WarehouseRequest warehouseRequest) {
        Warehouse warehouse = getWarehouseEntityById(id);

        warehouse.setName(warehouseRequest.getName());
        warehouse.setAddress(warehouseRequest.getAddress());
        warehouse.setCity(warehouseRequest.getCity());
        warehouse.setCapacity(warehouseRequest.getCapacity());

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return toResponse(savedWarehouse);
    }
    public void deleteWarehouseById(Long id){
        if(shipmentRepository.existsByWarehouseId(id)){
            throw new IllegalStateException("Cannot delete warehouse with id " + id + " because it has related shipments");
        }
        findWarehouseById(id);
        warehouseRepository.deleteById(id);
    }

}
