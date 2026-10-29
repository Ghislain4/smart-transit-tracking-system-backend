package com.smarttransit.repository;

import com.smarttransit.model.StopReport;
import com.smarttransit.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StopReportRepository extends JpaRepository<StopReport, UUID> {

    @Query("SELECT s FROM StopReport s WHERE s.trip.commuter.cardNumber = :cardNumber " +
            "ORDER BY  s.reportedDate DESC")
    List<StopReport> findReportsOfCommuter(@Param("cardNumber") String cardNumber);

    boolean existsByTrip(Trip trip);

}
