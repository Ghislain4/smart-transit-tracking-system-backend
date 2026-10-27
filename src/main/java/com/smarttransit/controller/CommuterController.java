package com.smarttransit.controller;

import com.smarttransit.model.Driver;
import com.smarttransit.service.DriverService;
import com.smarttransit.model.Commuter;
import com.smarttransit.service.CommuterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/commuters")
public class CommuterController {

    @Autowired
    private CommuterService commuterService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Commuter commuter) {
        String message = commuterService.save(commuter);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Commuter> getAll() {
        return commuterService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Commuter> commuter = commuterService.getById(id);
        if (commuter.isEmpty()) {
            return new ResponseEntity<>("The commuter with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commuter.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Commuter commuter) {
        String message = commuterService.update(id, commuter);
        if (message.equals("The commuter with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = commuterService.delete(id);
        if (message.equals("The commuter with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-last-name")
    public List<Commuter> getByLastName(@RequestParam String lastName) {
        return commuterService.getByLastName(lastName);
    }

    @Autowired
    private DriverService driverService;

    @GetMapping("/of-driver/{driverId}")
    public ResponseEntity<?> getCommutersOfDriver(@PathVariable UUID driverId) {
        Optional<Driver> driver = driverService.getById(driverId);
        if (driver.isEmpty()) {
            return new ResponseEntity<>("The driver with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commuterService.getCommutersOfDriver(driverId), HttpStatus.OK);
    }
}