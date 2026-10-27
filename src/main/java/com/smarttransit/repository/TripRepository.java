package com.smarttransit.repository;

import com.smarttransit.model.Trip;
import com.smarttransit.model.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    // B2: deleiver query (enum parammeter + orderby)
    List<Trip> findByStatusOrderByTripDateAsc(TripStatus status);

    // B3: deliver query (date range + orderby)
    List<Trip> findByTripDateBetweenOrderByTripDateAsc(LocalDate start, LocalDate end);
}