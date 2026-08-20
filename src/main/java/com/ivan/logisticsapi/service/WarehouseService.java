package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.model.Warehouse;
import com.ivan.logisticsapi.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }
    public List<Warehouse> getWarehouses(){
        return warehouseRepository.findAll();
    }
    public Warehouse findWarehouseById(Long id){
        Optional<Warehouse> warehouseOprtional = warehouseRepository.findById(id);
        return warehouseOprtional.orElseThrow(NoSuchElementException::new);
    }
    public Warehouse addWarehouse(Warehouse warehouse){
       return warehouseRepository.save(warehouse);
    }
    public void deleteWarehouseById(Long id){
        findWarehouseById(id);
        warehouseRepository.deleteById(id);
    }

}
