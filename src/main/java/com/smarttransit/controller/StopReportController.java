package com.smarttransit.controller;

import com.smarttransit.model.StopReport;
import com.smarttransit.service.StopReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stop-reports")

public class StopReportController {

    @Autowired
    private StopReportService stopReportService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID tripId, @RequestBody StopReport report) {
        String message = stopReportService.save(tripId, report);
        if (message.equals("The trip with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        if (message.equals("Stop reports are only for ARRIVED trips")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/commuter/{cardNumber}")
    public List<StopReport> getByCommuter(@PathVariable String cardNumber) {
        return stopReportService.getByCommuter(cardNumber);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = stopReportService.delete(id);
        if (message.equals("The stop report with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<StopReport> getAll() {
        return stopReportService.getAll();
    }

}
