package com.smarttransit.service;

import com.smarttransit.model.TripStatus;
import com.smarttransit.repository.BusRepository;
import com.smarttransit.repository.CommuterRepository;
import com.smarttransit.repository.DriverRepository;
import com.smarttransit.repository.StopReportRepository;
import com.smarttransit.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private CommuterRepository commuterRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private StopReportRepository stopReportRepository;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("commuters", commuterRepository.count());
        summary.put("drivers", driverRepository.count());
        summary.put("freeBuses", (long) busRepository.findFreeBuses().size());
        summary.put("tripsToday", tripRepository.countByTripDate(LocalDate.now()));
        summary.put("planned", tripRepository.countByStatus(TripStatus.PLANNED));
        summary.put("stopReports", stopReportRepository.count());
        return summary;
    }
}