package org.gordeser.user_service.entitiy;

import lombok.Getter;

@Getter
public enum EventStatus {
    PLANNED("Planned"),
    IN_PROGRESS("In progress"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");
    private final String status;

    EventStatus(String status) {
        this.status = status;
    }
}
