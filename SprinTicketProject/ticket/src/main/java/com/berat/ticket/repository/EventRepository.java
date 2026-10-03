package com.berat.ticket.repository;

import com.berat.ticket.entity.Event;
import com.berat.ticket.entity.EventState;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
	
	List<Event> findByState(EventState state);

}

