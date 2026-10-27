package com.smarttransit.service;

import com.smarttransit.model.Bus;
import com.smarttransit.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public String save(Bus bus) {
        busRepository.save(bus);
        return "Bus saved successfully";
    }

    public List<Bus> getAll() {
        return busRepository.findAll();
    }

    public Optional<Bus> getById(UUID id) {
        return busRepository.findById(id);
    }

    public String update(UUID id, Bus bus) {
        Optional<Bus> found = busRepository.findById(id);
        if (found.isEmpty()) {
            return "The bus with that id does not exist";
        }
        bus.setId(id);
        busRepository.save(bus);
        return "Bus updated successfully";
    }

    public String delete(UUID id) {
        if (!busRepository.existsById(id)) {
            return "The bus with that id does not exist";
        }
        busRepository.deleteById(id);
        return "Bus deleted successfully";
    }

    public List<Bus> getFreeBuses() {
        return busRepository.findFreeBuses();
    }
}