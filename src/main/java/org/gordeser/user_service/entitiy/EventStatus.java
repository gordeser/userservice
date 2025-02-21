package org.gordeser.user_service.entitiy;

public enum EventStatus {
    PLANNED("Planned"),
    IN_PROGRESS("In progress"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private final String status;

    EventStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return status;
    }
}
