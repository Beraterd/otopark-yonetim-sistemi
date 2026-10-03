package com.berat.ticket.dto;

public class FreeSeatDTO {
    private Long idSeat;
    private String row;
    private int seatNumber;

    public FreeSeatDTO() {}

    public FreeSeatDTO(Long idSeat, String row, int seatNumber) {
        this.idSeat = idSeat;
        this.row = row;
        this.seatNumber = seatNumber;
    }

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
}
