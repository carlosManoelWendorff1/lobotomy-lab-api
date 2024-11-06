package com.typingTitans.lobotomy_lab.repositories;

import com.typingTitans.lobotomy_lab.entities.Lab;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepository extends MongoRepository<Lab, Long>{

    public Optional<Lab> findByRoomName(String roomName);
    
    public List<Lab> findByComputers(long computers);
    
    public List<Lab> findByAvailableSoftwaresContaining(String software);
    
}
