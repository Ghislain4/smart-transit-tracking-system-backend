package com.smarttransit.repository;

import com.smarttransit.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID> {

    @Query("SELECT r FROM Route r WHERE r.drivers IS EMPTY")
    List<Route> findUnusedRoutes();

}