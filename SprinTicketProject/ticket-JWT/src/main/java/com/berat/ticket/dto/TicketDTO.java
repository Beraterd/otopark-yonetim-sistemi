package com.berat.ticket.dto;

import java.time.OffsetDateTime;

import com.berat.ticket.entity.Ticket;

public class TicketDTO {

    private Long id;
    private String ownerUsername;
    private String state;
    private OffsetDateTime createdAt;

    private Long eventId;
    private String eventName;

    private Long seatId;
    private String seatRow;
    private int seatNumber;

    public TicketDTO() {}

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.ownerUsername = ticket.getOwnerUsername();
        this.state = ticket.getState().toString();
        this.createdAt = ticket.getCreatedAt();

        if (ticket.getEvent() != null) {
            this.eventId = ticket.getEvent().getId();
            this.eventName = ticket.getEvent().getName();
        }

        if (ticket.getSeat() != null) {
            this.seatId = ticket.getSeat().getIdSeat();
            this.seatRow = ticket.getSeat().getRow();
            this.seatNumber = ticket.getSeat().getSeatNumber();
        }
    }

    public static TicketDTO fromEntity(Ticket ticket) {
        return new TicketDTO(ticket);
    }

    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOwnerUsername() { return ownerUsername; }
    public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public Long getSeatId() { return seatId; }
    public void setSeatId(Long seatId) { this.seatId = seatId; }

    public String getSeatRow() { return seatRow; }
    public void setSeatRow(String seatRow) { this.seatRow = seatRow; }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
}
