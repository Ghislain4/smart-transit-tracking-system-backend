package com.smarttransit.service;

import com.smarttransit.model.Bus;
import com.smarttransit.model.Driver;
import com.smarttransit.model.Route;
import com.smarttransit.repository.BusRepository;
import com.smarttransit.repository.DriverRepository;
import com.smarttransit.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRepository busRepository;

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

    // C1
    public List<Driver> getByRoute(String name) {
        return driverRepository.findByRouteName(name);
    }

    // D3: add one route to a driver
    @Transactional
    public String addRoute(UUID driverId, UUID routeId) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if (driver.isEmpty()) {
            return "The driver with that id does not exist";
        }
        Optional<Route> route = routeRepository.findById(routeId);
        if (route.isEmpty()) {
            return "That route does not exist";
        }
        Driver d = driver.get();
        Route r = route.get();
        if (d.getRoutes().contains(r)) {
            return "The driver already has that route";
        }
        d.getRoutes().add(r);
        driverRepository.save(d);
        return "Route added to the driver";
    }

    // D4: move a driver to another bus
    public String changeBus(UUID driverId, int busNumber) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if (driver.isEmpty()) {
            return "The driver with that id does not exist";
        }
        Optional<Bus> bus = busRepository.findByBusNumber(busNumber);
        if (bus.isEmpty()) {
            return "The bus with that busNumber does not exist";
        }
        Driver d = driver.get();
        Bus b = bus.get();
        boolean sameBus = d.getBus() != null && d.getBus().getId().equals(b.getId());
        if (!sameBus && driverRepository.existsByBus(b)) {
            return "That bus is taken by another driver";
        }
        d.setBus(b);
        driverRepository.save(d);
        return "Bus changed successfully";
    }
}