package com.berat.ticket.controller;

import com.berat.ticket.dto.FreeSeatDTO;
import com.berat.ticket.service.SeatService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{eventId}/seats")
    public List<FreeSeatDTO> getAllSeats(@PathVariable Long eventId) {
        return seatService.getAllSeatsForEvent(eventId);
    }
    
    @PostMapping("/{eventId}/seats/{seatId}/lock")
    public ResponseEntity<?> lockSeat(@PathVariable Long eventId, @PathVariable Long seatId) {
        try {
            seatService.lockSeat(eventId, seatId); // SeatService üzerinden çağıracağız
            return ResponseEntity.ok("Seat locked successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
    @GetMapping("/{eventId}/seats/free")
    public List<FreeSeatDTO> getFreeSeats(@PathVariable Long eventId) {
        return seatService.getFreeSeatsForEvent(eventId);
    }



}
