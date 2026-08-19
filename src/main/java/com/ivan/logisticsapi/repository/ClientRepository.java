package com.ivan.logisticsapi.repository;

import com.ivan.logisticsapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
