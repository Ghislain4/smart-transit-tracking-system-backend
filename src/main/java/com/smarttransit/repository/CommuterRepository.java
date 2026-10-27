package com.smarttransit.repository;

import com.smarttransit.model.Commuter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CommuterRepository extends JpaRepository<Commuter, UUID> {

    List<Commuter> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT t.commuter FROM Trip t WHERE t.driver.id = :driverId")
    List<Commuter> findCommutersOfDriver(@Param("driverId") UUID driverId);
}