package com.smarttransit.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate tripDate;

    @Enumerated(EnumType.STRING)
    private TripStatus status;

    private String boardingStop;
    private int etaMinutes;

    @ManyToOne
    @JoinColumn(name = "commuter_id")
    private Commuter commuter;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Trip() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public String getBoardingStop() {
        return boardingStop;
    }

    public void setBoardingStop(String boardingStop) {
        this.boardingStop = boardingStop;
    }

    public int getEtaMinutes() {
        return etaMinutes;
    }

    public void setEtaMinutes(int etaMinutes) {
        this.etaMinutes = etaMinutes;
    }

    public Commuter getCommuter() {
        return commuter;
    }

    public void setCommuter(Commuter commuter) {
        this.commuter = commuter;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}