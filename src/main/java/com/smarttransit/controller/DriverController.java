package com.smarttransit.controller;

import com.smarttransit.model.Driver;
import com.smarttransit.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Driver driver) {
        String message = driverService.save(driver);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Driver> getAll() {
        return driverService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Driver> driver = driverService.getById(id);
        if (driver.isEmpty()) {
            return new ResponseEntity<>("The driver with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(driver.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Driver driver) {
        String message = driverService.update(id, driver);
        if (message.equals("The driver with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = driverService.delete(id);
        if (message.equals("The driver with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}