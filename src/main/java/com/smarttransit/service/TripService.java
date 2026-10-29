package com.smarttransit.service;

import org.springframework.transaction.annotation.Transactional;
import com.smarttransit.repository.StopReportRepository;
import com.smarttransit.model.Trip;
import com.smarttransit.model.TripStatus;
import com.smarttransit.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private StopReportRepository stopReportRepository;

    // D1: safe saving
    public String save(Trip trip) {
        if (trip.getTripDate().isBefore(LocalDate.now())) {
            return "Trip date cannot be in the past";
        }
        boolean busy = tripRepository.existsByDriverIdAndTripDateAndStatusNot(
                trip.getDriver().getId(), trip.getTripDate(), TripStatus.CANCELLED);
        if (busy) {
            return "Driver already has a trip on that date";
        }
        trip.setStatus(TripStatus.PLANNED);
        tripRepository.save(trip);
        return "Trip saved successfully";
    }

    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getById(UUID id) {
        return tripRepository.findById(id);
    }

    // D2: the general update can no longer change the status
    public String update(UUID id, Trip trip) {
        Optional<Trip> found = tripRepository.findById(id);
        if (found.isEmpty()) {
            return "The trip with that id does not exist";
        }
        trip.setId(id);
        trip.setStatus(found.get().getStatus());
        tripRepository.save(trip);
        return "Trip updated successfully";
    }

    public String delete(UUID id) {
        Optional<Trip> found = tripRepository.findById(id);
        if (found.isEmpty()) {
            return "The trip with that does not exist";
        }
        if (stopReportRepository.existsByTrip(found.get())) {
            return "That trip has stop reports, delete them first";
        }
        tripRepository.deleteById(id);
        return "Trip delete successfully";
    }

    // B2
    public List<Trip> getByStatus(TripStatus status) {
        return tripRepository.findByStatusOrderByTripDateAsc(status);
    }

    // B3
    public List<Trip> getBetween(LocalDate start, LocalDate end) {
        return tripRepository.findByTripDateBetweenOrderByTripDateAsc(start, end);
    }

    // D2: status flow
    public String updateStatus(UUID id, TripStatus newStatus) {
        Optional<Trip> found = tripRepository.findById(id);
        if (found.isEmpty()) {
            return "The trip with that id does not exist";
        }
        Trip trip = found.get();
        TripStatus current = trip.getStatus();

        boolean allowed = false;
        if (current == TripStatus.PLANNED
                && (newStatus == TripStatus.DEPARTED || newStatus == TripStatus.CANCELLED)) {
            allowed = true;
        }
        if (current == TripStatus.DEPARTED
                && (newStatus == TripStatus.ARRIVED || newStatus == TripStatus.CANCELLED)) {
            allowed = true;
        }

        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        trip.setStatus(newStatus);
        tripRepository.save(trip);
        return "Trip status updated successfully";
    }

    public List<Object[]> getStatsByStatus() {
        return tripRepository.countTripsByStatus();
    }

    @Transactional
    public String cancelDay(UUID driverId, LocalDate date) {
        int count = tripRepository.cancelDay(driverId, date, TripStatus.CANCELLED, TripStatus.ARRIVED);
        return count + " trips cancelled";
    }
}