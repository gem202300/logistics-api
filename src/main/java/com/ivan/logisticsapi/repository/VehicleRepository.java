package com.ivan.logisticsapi.repository;

import com.ivan.logisticsapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

}
