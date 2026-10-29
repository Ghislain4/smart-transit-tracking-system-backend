package com.smarttransit.controller;

import com.smarttransit.model.TripStatus;
import java.time.LocalDate;

import com.smarttransit.model.Trip;
import com.smarttransit.model.TripStatus;
import com.smarttransit.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Trip trip) {
        String message = tripService.save(trip);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Trip> getAll() {
        return tripService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Trip> trip = tripService.getById(id);
        if (trip.isEmpty()) {
            return new ResponseEntity<>("The trip with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trip.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Trip trip) {
        String message = tripService.update(id, trip);
        if (message.equals("The trip with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = tripService.delete(id);
        if (message.equals("The trip with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.equals("That trip has stop reports, delete them first")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public List<Trip> getByStatus(@RequestParam TripStatus status) {
        return tripService.getByStatus(status);
    }

    @GetMapping("/between")
    public List<Trip> GetBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return tripService.getBetween(startDate, endDate);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable UUID id, @RequestParam TripStatus status) {
        String message = tripService.updateStatus(id, status);
        if (message.equals("The trip with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.startsWith("cannot change status")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/stats/by-status")
    public List<Object[]> getStatusByStatus() {
        return tripService.getStatsByStatus();
    }

    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelDay(@RequestParam UUID driverId, @RequestParam String date) {
        LocalDate day = LocalDate.parse(date);
        String message = tripService.cancelDay(driverId, day);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}