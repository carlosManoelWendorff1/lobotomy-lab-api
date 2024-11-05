package com.typingTitans.lobotomy_lab.entities;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.typingTitans.lobotomy_lab.enuns.LabStatus;


@Document(collection = "labs")
public class Lab {
    
    private Long computers;
    private String location;
    private String roomName;
    private Boolean accesible;
    private LabStatus status;
    private List<String> availableSoftwares;

    

    public Lab(Long computers, String location, String roomName, Boolean accesible, LabStatus status,
            List<String> availableSoftwares) {
        this.computers = computers;
        this.location = location;
        this.roomName = roomName;
        this.accesible = accesible;
        this.status = status;
        this.availableSoftwares = availableSoftwares;
    }


    public Long getComputers() {
        return computers;
    }

    public void setComputers(Long computers) {
        this.computers = computers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getroomName() {
        return roomName;
    }

    public void setroomName(String roomName) {
        this.roomName = roomName;
    }

    public Boolean getAccesible() {
        return accesible;
    }

    public void setAccesible(Boolean accesible) {
        this.accesible = accesible;
    }

    public LabStatus getStatus() {
        return status;
    }

    public void setStatus(LabStatus status) {
        this.status = status;
    }

    public List<String> getAvailableSoftwares() {
        return availableSoftwares;
    }

    public void setAvailableSoftwares(List<String> availableSoftwares) {
        this.availableSoftwares = availableSoftwares;
    }

    

}
