package com.ivan.logisticsapi.controller;

import com.ivan.logisticsapi.model.Warehouse;
import com.ivan.logisticsapi.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
        ("/api/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public Warehouse addWarehouse(@Valid @RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }
    @GetMapping
    public List<Warehouse> getWarehouses(){
        return warehouseService.getWarehouses();
    }
    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable Long id){
        return warehouseService.findWarehouseById(id);
    }

    @PutMapping("/{id}")
    public Warehouse  updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody Warehouse warehouse){
        Warehouse tempWarehouse = warehouseService.findWarehouseById(id);

        tempWarehouse.setName(warehouse.getName());
        tempWarehouse.setAddress(warehouse.getAddress());
        tempWarehouse.setCapacity(warehouse.getCapacity());
        tempWarehouse.setCity(warehouse.getCity());

        return warehouseService.addWarehouse(tempWarehouse);
    }
    @DeleteMapping("/{id}")
    public void deleteWarehouseById(@PathVariable Long id){
        warehouseService.deleteWarehouseById(id);
    }

}
