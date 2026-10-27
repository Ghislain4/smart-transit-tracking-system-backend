package com.smarttransit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "routes")
    @JsonIgnore
    private List<Driver> Drivers;

    public Route() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Driver> getDrivers() {
        return Drivers;
    }

    public void setDrivers(List<Driver> Drivers) {
        this.Drivers = Drivers;
    }
}
