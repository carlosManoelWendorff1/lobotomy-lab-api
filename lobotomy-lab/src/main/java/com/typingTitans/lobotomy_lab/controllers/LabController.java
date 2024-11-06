package com.typingTitans.lobotomy_lab.controllers;

import com.typingTitans.lobotomy_lab.entities.Lab;
import com.typingTitans.lobotomy_lab.services.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labs")
public class LabController{

    @Autowired
    private LabService labService;

    @GetMapping
    public List<Lab> getAllLabs() {
        return labService.findAll();
    }
    
    @GetMapping("/findLab")
    public ResponseEntity<List<Lab>> findLab(
            @RequestParam(required = false) String roomName,
            @RequestParam(required = false) Long computers,
            @RequestParam(required = false) String software) {
    
        List<Lab> labs = labService.findByFilters(roomName, computers, software);
    
        if (labs.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(labs);
        }
    }
    


    @PostMapping
    public HttpStatus createLab(@RequestBody Lab lab) {
        return labService.save(lab);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lab> updateLab(@PathVariable Long id, @RequestBody Lab lab) {
        try {
            Lab updatedLab = labService.update(id, lab);
            return ResponseEntity.ok(updatedLab);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLab(@PathVariable Long id) {
        labService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
