package com.berat.ticket.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "SeatLock",
    uniqueConstraints = @UniqueConstraint(columnNames = {"IdEvent", "IdSeat"})
)
@Getter
@Setter
@NoArgsConstructor
public class SeatLock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLock")
    private Long IdLock;

    @ManyToOne
    @JoinColumn(name = "IdEvent")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "IdSeat")
    private Seat seat;

    @Column(name = "ValidUntil")
    private OffsetDateTime validUntil;
    public void setEvent(Event event) { this.event = event; }
    public void setSeat(Seat seat) { this.seat = seat; }
    public void setValidUntil(OffsetDateTime validUntil) { this.validUntil = validUntil; }

}
