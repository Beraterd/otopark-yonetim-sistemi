package com.berat.ticket.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSeat")
    private Long idSeat;

    @Column(name = "Row")
    private String row;

    @Column(name = "SeatNumber")
    private int seatNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IdLayout", referencedColumnName = "IdLayout", nullable = false)
    @JsonIgnore
    private PhysicalSeatLayout seatLayout;

    // Getters and Setters
    public Long getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Long idSeat) {
        this.idSeat = idSeat;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public PhysicalSeatLayout getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(PhysicalSeatLayout seatLayout) {
        this.seatLayout = seatLayout;
    }
}
