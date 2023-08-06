package com.company.oop.taskmanagement.models;

import com.company.oop.taskmanagement.models.contracts.ActivityHistory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLog implements ActivityHistory {

    private String description;
    private final LocalDateTime timestamp;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");

    public EventLog(String description) {
        setDescribtion(description);
        timestamp = LocalDateTime.now();
    }

    private void setDescribtion(String description) {
        this.description = description;
    }

    protected String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String dateFormat = timestamp.format(formatter);
        return String.format("[%s] %s", dateFormat, getDescription());

    }
}
