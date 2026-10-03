package com.berat.ticket.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.berat.ticket.dto.TicketDTO;
import com.berat.ticket.entity.Event;
import com.berat.ticket.entity.Seat;
import com.berat.ticket.entity.SeatLock;
import com.berat.ticket.entity.Ticket;
import com.berat.ticket.entity.User;
import com.berat.ticket.repository.EventRepository;
import com.berat.ticket.repository.SeatLockRepository;
import com.berat.ticket.repository.SeatRepository;
import com.berat.ticket.repository.TicketRepository;
import com.berat.ticket.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class TicketService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatLockRepository seatLockRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Ticket createTicket(Long eventId, Long seatId, String username) {
        entityManager.createStoredProcedureQuery("DeleteOldSeatLocks").execute();

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        // Koltuğu 5 dakika kilitle
        SeatLock seatLock = new SeatLock();
        seatLock.setEvent(event);
        seatLock.setSeat(seat);
        seatLock.setValidUntil(OffsetDateTime.now().plusMinutes(5));

        try {
            seatLockRepository.save(seatLock);
        } catch (Exception e) {
            throw new RuntimeException("Seat is currently locked by another user");
        }

        // Satılmış mı kontrol et
        Optional<Ticket> existingTicket = ticketRepository.findByEventAndSeatAndState(event, seat, TicketState.VALID);
        if (existingTicket.isPresent()) {
            seatLockRepository.delete(seatLock);
            throw new RuntimeException("Seat already sold");
        }

        // Kullanıcıyı bul
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Ticket oluştur
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setSeat(seat);
        ticket.setUser(user);
        ticket.setCreatedAt(OffsetDateTime.now());
        ticket.setState(TicketState.VALID);

        ticketRepository.save(ticket);
        seatLockRepository.delete(seatLock);

        return ticket;
    }

    public List<TicketDTO> getTicketsForUser(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Ticket> tickets = ticketRepository.findByUser(user);
        return tickets.stream()
                .map(TicketDTO::fromEntity)
                .toList();
    }


    @Transactional
    public void cancelTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        if (ticket.getState() == TicketState.CANCELLED) {
            throw new RuntimeException("Ticket is already cancelled.");
        }
        ticket.setState(TicketState.CANCELLED);
        ticketRepository.saveAndFlush(ticket);
    }

    public List<Ticket> getTicketsForEvent(Long eventId) {
        return ticketRepository.findByEvent_Id(eventId);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
