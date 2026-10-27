package com.smarttransit.repository;

import com.smarttransit.model.Commuter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommuterRepository extends JpaRepository<Commuter, UUID> {

    List<Commuter> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}