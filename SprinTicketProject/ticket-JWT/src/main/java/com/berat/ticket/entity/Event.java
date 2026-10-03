package com.berat.ticket.entity;

import java.time.OffsetDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "planned_start_time")  // SQL'deki gerçek kolon adı
    private OffsetDateTime plannedStartTime;



    @Column(name = "State")
    @Enumerated(EnumType.STRING)
    private EventState state;

    
    // Getter-Setter'lar

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

    public PhysicalSeatLayout getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(PhysicalSeatLayout seatLayout) {
        this.seatLayout = seatLayout;
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }
    @ManyToOne
    @JoinColumn(name = "id_physical_seat_layout")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private PhysicalSeatLayout seatLayout;

}
