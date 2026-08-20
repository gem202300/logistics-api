package com.ivan.logisticsapi.repository;

import com.ivan.logisticsapi.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {
}
