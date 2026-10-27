package com.smarttransit.repository;

import com.smarttransit.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
}