package com.typingTitans.lobotomy_lab.enuns;

public enum LabStatus {
    AVAILABLE("The lab is available for use"),
    OCCUPIED("The lab is currently occupied"),
    MAINTENANCE_IN_PROGRESS("Maintenance is in progress"),
    BLOCKED("The lab is blocked");

    private final String description;

    LabStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
