package com.smarttransit.repository;

import com.smarttransit.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BusRepository extends JpaRepository<Bus, UUID> {

    @Query("SELECT b FROM Bus b WHERE NOT EXISTS" +
            "(SELECT d FROM Driver d WHERE d.bus = b) ORDER BY b.busNumber")
    List<Bus> findFreeBuses();

    Optional<Bus> findByBusNumber(int busNumber);

}