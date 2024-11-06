package com.typingTitans.lobotomy_lab.repositories;

import org.springframework.stereotype.Repository;

import com.typingTitans.lobotomy_lab.entities.User;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    public User findByUsername(String username);
    
}
