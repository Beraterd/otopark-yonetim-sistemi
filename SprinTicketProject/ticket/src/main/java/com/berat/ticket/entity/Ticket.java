package com.berat.ticket.entity;

import java.time.OffsetDateTime;

import com.berat.ticket.service.TicketState;

import jakarta.persistence.*;

@Entity
@Table(
    name = "Ticket",
    uniqueConstraints = @UniqueConstraint(columnNames = {"IdEvent", "IdSeat", "State"})
)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTicket")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IdEvent")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "IdSeat")
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdUser")
    private User user;

    @Column(name = "TicketState")
    private TicketState state;

    @Column(name = "CreatedAt")
    private OffsetDateTime createdAt;

    public Ticket() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public Seat getSeat() { return seat; }
    public void setSeat(Seat seat) { this.seat = seat; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public TicketState getState() { return state; }
    public void setState(TicketState state) { this.state = state; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    // Yardımcı
    public String getOwnerUsername() {
        return user != null ? user.getUsername() : null;
    }
}
