package org.gordeser.user_service.entitiy;

import lombok.Getter;

@Getter
public enum EventType {
    WEBINAR("Webinar"),
    POLL("Poll"),
    Meeting("Meeting"),
    Giveaway("Giveaway"),
    Presentation("Presentatiion");

    private final String type;

    EventType(String type) {
        this.type = type;
    }
}
