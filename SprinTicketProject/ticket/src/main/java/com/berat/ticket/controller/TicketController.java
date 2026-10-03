package com.berat.ticket.controller;

import com.berat.ticket.dto.TicketDTO;

import org.springframework.security.core.Authentication;
import com.berat.ticket.entity.Ticket;
import com.berat.ticket.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<?> createTicket(
        @RequestParam Long eventId,
        @RequestParam Long seatId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            Ticket ticket = ticketService.createTicket(eventId, seatId, username);
            return ResponseEntity.ok(ticket);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PostMapping("/{ticketId}/cancel")
    public ResponseEntity<?> cancelTicket(@PathVariable Long ticketId) {
        try {
            ticketService.cancelTicket(ticketId);
            return ResponseEntity.ok("Ticket cancelled successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/by-event/{eventId}")
    public ResponseEntity<List<TicketDTO>> getTicketsForEvent(@PathVariable Long eventId) {
        List<Ticket> tickets = ticketService.getTicketsForEvent(eventId);
        List<TicketDTO> ticketDTOs = tickets.stream()
                .map(TicketDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ticketDTOs);
    }
    @GetMapping("/my-tickets")
    public ResponseEntity<List<TicketDTO>> getMyTickets() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<TicketDTO> myTickets = ticketService.getTicketsForUser(username);
        return ResponseEntity.ok(myTickets);
    }



}
