package com.smarttransit.service;

import com.smarttransit.model.Route;
import com.smarttransit.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public String save(Route route) {
        routeRepository.save(route);
        return "Route saved successfully";
    }

    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    public Optional<Route> getById(UUID id) {
        return routeRepository.findById(id);
    }

    public String update(UUID id, Route route) {
        Optional<Route> found = routeRepository.findById(id);
        if (found.isEmpty()) {
            return "The route with that id does not exist";
        }
        route.setId(id);
        routeRepository.save(route);
        return "Route updated successfully";
    }

    public String delete(UUID id) {
        if (!routeRepository.existsById(id)) {
            return "The route with that id does not exist";
        }
        routeRepository.deleteById(id);
        return "Route deleted successfully";
    }
}