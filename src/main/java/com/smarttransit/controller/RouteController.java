package com.smarttransit.controller;

import com.smarttransit.model.Route;
import com.smarttransit.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Route route) {
        String message = routeService.save(route);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Route> getAll() {
        return routeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Route> route = routeService.getById(id);
        if (route.isEmpty()) {
            return new ResponseEntity<>("The route with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(route.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Route route) {
        String message = routeService.update(id, route);
        if (message.equals("The route with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = routeService.delete(id);
        if (message.equals("The route with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}