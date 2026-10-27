package com.smarttransit.service;

import com.smarttransit.model.Driver;
import com.smarttransit.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public String save(Driver driver) {
        driverRepository.save(driver);
        return "Driver saved successfully";
    }

    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getById(UUID id) {
        return driverRepository.findById(id);
    }

    public String update(UUID id, Driver driver) {
        Optional<Driver> found = driverRepository.findById(id);
        if (found.isEmpty()) {
            return "The driver with that id does not exist";
        }
        driver.setId(id);
        driverRepository.save(driver);
        return "Driver updated successfully";
    }

    public String delete(UUID id) {
        if (!driverRepository.existsById(id)) {
            return "The driver with that id does not exist";
        }
        driverRepository.deleteById(id);
        return "Driver deleted successfully";
    }

    public List<Driver> getByRoute(String name) {
        return driverRepository.findByRouteName(name);
    }
}