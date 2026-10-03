package com.berat.ticket.service;

import com.berat.ticket.entity.Event;
import com.berat.ticket.entity.Seat;
import com.berat.ticket.entity.SeatLock;
import com.berat.ticket.repository.EventRepository;
import com.berat.ticket.repository.SeatLockRepository;
import com.berat.ticket.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class SeatLockService {

    private final EventRepository eventRepository;
    private final SeatRepository seatRepository;
    private final SeatLockRepository seatLockRepository;

    public SeatLockService(EventRepository eventRepository,
                           SeatRepository seatRepository,
                           SeatLockRepository seatLockRepository) {
        this.eventRepository = eventRepository;
        this.seatRepository = seatRepository;
        this.seatLockRepository = seatLockRepository;
    }

    @Transactional
    public void lockSeat(Long eventId, Long seatId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        SeatLock seatLock = new SeatLock();
        seatLock.setEvent(event);
        seatLock.setSeat(seat);
        seatLock.setValidUntil(OffsetDateTime.now().plusMinutes(5)); // örnek süre

        try {
            seatLockRepository.save(seatLock);
        } catch (Exception e) {
            throw new RuntimeException("Seat is already locked.");
        }
    }
}
