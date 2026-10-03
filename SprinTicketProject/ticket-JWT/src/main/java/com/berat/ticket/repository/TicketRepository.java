package com.berat.ticket.repository;

import com.berat.ticket.entity.Event;
import com.berat.ticket.entity.Seat;
import com.berat.ticket.entity.Ticket;
import com.berat.ticket.entity.User;
import com.berat.ticket.service.TicketState;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	List<Ticket> findByEvent_Id(Long eventId);
    Optional<Ticket> findByEventAndSeatAndState(Event event, Seat seat, TicketState state);
    List<Ticket> findByUser(User user);

}

