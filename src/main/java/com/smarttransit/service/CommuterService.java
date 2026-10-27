package com.smarttransit.service;

import com.smarttransit.model.Commuter;
import com.smarttransit.repository.CommuterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommuterService {

    @Autowired
    private CommuterRepository commuterRepository;

    public String save(Commuter commuter) {
        commuterRepository.save(commuter);
        return "Commuter saved successfully";
    }

    public List<Commuter> getAll() {
        return commuterRepository.findAll();
    }

    public Optional<Commuter> getById(UUID id) {
        return commuterRepository.findById(id);
    }

    public String update(UUID id, Commuter commuter) {
        Optional<Commuter> found = commuterRepository.findById(id);
        if (found.isEmpty()) {
            return "The commuter with that id does not exist";
        }
        commuter.setId(id);
        commuterRepository.save(commuter);
        return "Commuter updated successfully";
    }

    public String delete(UUID id) {
        if (!commuterRepository.existsById(id)) {
            return "The commuter with that id does not exist";
        }
        commuterRepository.deleteById(id);
        return "Commuter deleted successfully";
    }

    public List<Commuter> getByLastName(String lastName) {
        return commuterRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public List<Commuter> getCommutersOfDriver(UUID driverId) {
        return commuterRepository.findCommutersOfDriver(driverId);
    }
}