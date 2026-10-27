package com.smarttransit.repository;

import com.smarttransit.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BusRepository extends JpaRepository<Bus, UUID> {
}