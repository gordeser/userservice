package org.gordeser.user_service.entitiy;

public enum EventType {
    WEBINAR("Webinar"),
    POLL("Poll"),
    MEETINGS("Meeting"),
    GIVEAWAY("Giveaway"),
    PRESENTATION("Presentatiion");

    private final String type;

    EventType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return type;
    }
}
