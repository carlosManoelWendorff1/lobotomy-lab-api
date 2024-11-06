package com.typingTitans.lobotomy_lab.services;

import com.typingTitans.lobotomy_lab.entities.Lab;
import com.typingTitans.lobotomy_lab.repositories.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LabRepository labRepository;

    public List<Lab> findAll() {
        return labRepository.findAll();
    }

    public Optional<Lab> findById(Long id) {
        return labRepository.findById(id);
    }

    public Optional<Lab> findByRoomName(String roomName) {
        return labRepository.findByRoomName(roomName);
    }

    public List<Lab> findByFilters(String roomName, Long computers, String software) {
        Query query = new Query();

        if (roomName != null) {
            query.addCriteria(Criteria.where("roomName").regex(roomName, "i"));
        }
        if (computers != null) {
            query.addCriteria(Criteria.where("computers").gte(computers));
        }
        if (software != null) {
            query.addCriteria(Criteria.where("availableSoftwares").in(software)); // Verifica se o software está na lista
        }

        return mongoTemplate.find(query, Lab.class);
    }
    
    public List<Lab> findByNumberOfComputers(Long computers) {
        return labRepository.findByComputers(computers);
    }

    public List<Lab> findBySoftware(String software) {
        return labRepository.findByAvailableSoftwaresContaining(software);
    }

    public HttpStatus save(Lab lab) {
        if(labRepository.findByRoomName(lab.getroomName()).isPresent()){
            return HttpStatus.CONFLICT;
        }
        labRepository.save(lab);
        return HttpStatus.CREATED;
    }

    public Lab update(Long id, Lab updatedLab) {
        return labRepository.findById(id).map(lab -> {
            lab.setComputers(updatedLab.getComputers());
            lab.setLocation(updatedLab.getLocation());
            lab.setroomName(updatedLab.getroomName());
            lab.setAccesible(updatedLab.getAccesible());
            lab.setStatus(updatedLab.getStatus());
            lab.setAvailableSoftwares(updatedLab.getAvailableSoftwares());
            return labRepository.save(lab);
        }).orElseThrow(() -> new RuntimeException("Lab not found"));
    }

    public void delete(Long id) {
        labRepository.deleteById(id);
    }
}
