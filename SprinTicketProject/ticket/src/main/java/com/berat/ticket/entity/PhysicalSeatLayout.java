package com.berat.ticket.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "PhysicalSeatLayout")
public class PhysicalSeatLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLayout")
    private Long IdLayout;

    @Column(name = "LayoutName")
    private String LayoutName;

    @OneToMany(mappedBy = "seatLayout")
    private List<Seat> seats;

    // Getters & Setters

    public Long getId() {
        return IdLayout;
    }

    public void setId(Long IdLayout) {
        this.IdLayout = IdLayout;
    }

    public String getName() {
        return LayoutName;
    }

    public void setName(String name) {
        this.LayoutName = name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
