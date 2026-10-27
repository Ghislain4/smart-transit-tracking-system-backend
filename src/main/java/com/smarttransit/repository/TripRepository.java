package com.smarttransit.repository;

import com.smarttransit.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
}