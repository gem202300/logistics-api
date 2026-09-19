package com.ivan.logisticsapi.controller;

import com.ivan.logisticsapi.dto.WarehouseRequest;
import com.ivan.logisticsapi.dto.WarehouseResponse;
import com.ivan.logisticsapi.model.Warehouse;
import com.ivan.logisticsapi.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public WarehouseResponse addWarehouse(@Valid @RequestBody WarehouseRequest warehouseRequest){
        return warehouseService.addWarehouse(warehouseRequest);
    }
    @GetMapping
    public List<WarehouseResponse> getWarehouses(){
        return warehouseService.getWarehouses();
    }

    @GetMapping("/{id}")
    public WarehouseResponse getWarehouseById(@PathVariable Long id){
        return warehouseService.findWarehouseById(id);
    }

    @PutMapping("/{id}")
    public WarehouseResponse updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseRequest warehouseRequest) {

        return warehouseService.updateWarehouseById(id, warehouseRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouseById(@PathVariable Long id){
        warehouseService.deleteWarehouseById(id);
        return ResponseEntity.noContent().build();
    }

}
