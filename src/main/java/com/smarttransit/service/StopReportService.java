package com.smarttransit.service;

import com.smarttransit.model.StopReport;
import com.smarttransit.model.Trip;
import com.smarttransit.model.TripStatus;
import com.smarttransit.repository.StopReportRepository;
import com.smarttransit.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StopReportService {

    private final StopReportRepository stopReportRepository;

    @Autowired
    private StopReportRepository stopReportrepository;

    @Autowired
    private TripRepository triprepository;

    StopReportService(StopReportRepository stopReportRepository) {
        this.stopReportRepository = stopReportRepository;
    }

    public String save(UUID tripId, StopReport report) {
        Optional<Trip> trip = triprepository.findById(tripId);
        if (trip.isEmpty()) {
            return "The trip with that id does not exist";
        }
        if (trip.get().getStatus() != TripStatus.ARRIVED) {
            return "Stop report are only for Arrived trips";
        }
        report.setReportedDate(LocalDate.now());
        report.setTrip(trip.get());
        stopReportrepository.save(report);
        return "Stop report saved successfully";
    }

    public List<StopReport> getAll() {
        return stopReportRepository.findAll();
    }

    public List<StopReport> getByCommuter(String cardNumber) {
        return stopReportRepository.findReportsOfCommuter(cardNumber);
    }

    public String delete(UUID id) {
        if (!stopReportRepository.existsById(id)) {
            return "The stop report with that id does not exist";
        }
        stopReportrepository.deleteById(id);
        return "Stop report delete successefully";
    }
}
