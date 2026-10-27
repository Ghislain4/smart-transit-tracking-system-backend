package com.smarttransit.controller;

import com.smarttransit.model.Bus;
import com.smarttransit.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Bus bus) {
        String message = busService.save(bus);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Bus> getAll() {
        return busService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Bus> bus = busService.getById(id);
        if (bus.isEmpty()) {
            return new ResponseEntity<>("The bus with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bus.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Bus bus) {
        String message = busService.update(id, bus);
        if (message.equals("The bus with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = busService.delete(id);
        if (message.equals("The bus with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/free")
    public List<Bus> getFreeBuses() {
        return busService.getFreeBuses();
    }
}