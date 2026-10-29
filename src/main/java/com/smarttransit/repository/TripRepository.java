package com.smarttransit.repository;

import com.smarttransit.model.Trip;
import com.smarttransit.model.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    // B2: deleiver query (enum parammeter + orderby)
    List<Trip> findByStatusOrderByTripDateAsc(TripStatus status);

    // B3: deliver query (date range + orderby)
    List<Trip> findByTripDateBetweenOrderByTripDateAsc(LocalDate start, LocalDate end);

    boolean existsByDriverIdAndTripDateAndStatusNot(UUID driverId, LocalDate tripDate, TripStatus status);

    @Query("SELECT t.status, COUNT(t) FROM Trip t GROUP BY t.status")
    List<Object[]> countTripsByStatus();

    // F2: JPQL, one UPDATE query (returns the number of changed rows)
    @Modifying
    @Query("UPDATE Trip t SET t.status = :newStatus " +
            "WHERE t.driver.id = :driverId AND t.tripDate = :day " +
            "AND t.status <> :keepStatus")
    int cancelDay(@Param("driverId") UUID driverId,
            @Param("day") LocalDate day,
            @Param("newStatus") TripStatus newStatus,
            @Param("keepStatus") TripStatus keepStatus);
}