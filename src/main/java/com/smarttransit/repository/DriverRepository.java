package com.smarttransit.repository;

import com.smarttransit.model.Bus;
import com.smarttransit.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {

    // C1: JPQL, JOIN on the routes collection + lower
    @Query("SELECT d FROM Driver d JOIN d.routes r " +
            "WHERE LOWER(r.name)  LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Driver> findByRouteName(@Param("name") String name);

    boolean existsByBus(Bus bus);
}