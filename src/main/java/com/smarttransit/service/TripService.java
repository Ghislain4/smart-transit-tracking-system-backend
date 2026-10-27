package com.smarttransit.service;

import com.smarttransit.model.Trip;
import com.smarttransit.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public String save(Trip trip) {
        tripRepository.save(trip);
        return "Trip saved successfully";
    }

    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getById(UUID id) {
        return tripRepository.findById(id);
    }

    public String update(UUID id, Trip trip) {
        Optional<Trip> found = tripRepository.findById(id);
        if (found.isEmpty()) {
            return "The trip with that id does not exist";
        }
        trip.setId(id);
        tripRepository.save(trip);
        return "Trip updated successfully";
    }

    public String delete(UUID id) {
        if (!tripRepository.existsById(id)) {
            return "The trip with that id does not exist";
        }
        tripRepository.deleteById(id);
        return "Trip deleted successfully";
    }
}