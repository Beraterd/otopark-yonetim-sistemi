package com.berat.ticket.dto;

import java.time.OffsetDateTime;

public class EventDTO {
    private Long id;
    private String name;
    private OffsetDateTime plannedStartTime;
    private String state;
    private String seatLayoutName;

    public EventDTO() {}

    public EventDTO(Long id, String name, OffsetDateTime plannedStartTime, String state, String seatLayoutName) {
        this.id = id;
        this.name = name;
        this.plannedStartTime = plannedStartTime;
        this.state = state;
        this.seatLayoutName = seatLayoutName;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getPlannedStartTime() {
        return plannedStartTime;
    }

    public void setPlannedStartTime(OffsetDateTime plannedStartTime) {
        this.plannedStartTime = plannedStartTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSeatLayoutName() {
        return seatLayoutName;
    }

    public void setSeatLayoutName(String seatLayoutName) {
        this.seatLayoutName = seatLayoutName;
    }
}
