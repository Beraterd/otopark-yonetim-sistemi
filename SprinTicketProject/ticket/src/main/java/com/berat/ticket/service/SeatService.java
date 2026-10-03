package com.berat.ticket.service;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.berat.ticket.dto.FreeSeatDTO;
import com.berat.ticket.entity.Event;
import com.berat.ticket.entity.Seat;
import com.berat.ticket.entity.SeatLock;
import com.berat.ticket.repository.EventRepository;
import com.berat.ticket.repository.SeatLockRepository;
import com.berat.ticket.repository.SeatRepository;

@Service
public class SeatService {

    private final EventRepository eventRepository;
    private final SeatRepository seatRepository;
    private final SeatLockRepository seatLockRepository;

    public SeatService(EventRepository eventRepository, SeatRepository seatRepository, SeatLockRepository seatLockRepository) {
        this.eventRepository = eventRepository;
        this.seatRepository = seatRepository;
        this.seatLockRepository = seatLockRepository;
    }

    // Etkinliğe ait tüm koltukları getir
    public List<FreeSeatDTO> getAllSeatsForEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return seatRepository.findBySeatLayout(event.getSeatLayout()).stream()
                .map(seat -> new FreeSeatDTO(
                        seat.getIdSeat(),
                        seat.getRow(),
                        seat.getSeatNumber()
                ))
                .collect(Collectors.toList());
    }

    // Etkinliğe ait boş koltukları getir (DTO dönüşümlü)
    public List<FreeSeatDTO> getFreeSeatsForEvent(Long eventId) {
        System.out.println("✅ getFreeSeatsForEvent() METODU ÇAĞRILDI");

        List<Object[]> results = seatRepository.getFreeSeatsForEvent(eventId);

        return results.stream()
                .map(obj -> {
                    Long idSeat = null;
                    String row = null;
                    int seatNumber = -1;

                    try {
                        System.out.println("🔍 Seat row: " + Arrays.toString(obj));

                        if (obj.length >= 2) {
                            idSeat = ((Number) obj[0]).longValue();
                            row = (String) obj[1];

                            if (obj.length >= 3 && obj[2] != null && obj[2] instanceof Number) {
                                seatNumber = ((Number) obj[2]).intValue();
                            } else {
                                System.out.println("⚠️ seatNumber eksik veya null: " + Arrays.toString(obj));
                            }
                        } else {
                            System.out.println("❌ Beklenmeyen veri formatı: " + Arrays.toString(obj));
                        }
                    } catch (Exception e) {
                        System.out.println("🚨 Hata oluştu: " + e.getMessage());
                    }

                    return new FreeSeatDTO(idSeat, row, seatNumber);
                })
                .collect(Collectors.toList());
    }



    // Koltuğu kilitle
    public void lockSeat(Long eventId, Long seatId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        SeatLock lock = new SeatLock();
        lock.setEvent(event);
        lock.setSeat(seat);
        lock.setValidUntil(OffsetDateTime.now().plusMinutes(5));

        try {
            seatLockRepository.save(lock);
        } catch (Exception e) {
            throw new RuntimeException("Seat is already locked.");
        }
    }
}
