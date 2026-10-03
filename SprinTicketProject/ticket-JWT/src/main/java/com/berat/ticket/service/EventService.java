package com.berat.ticket.service;

import com.berat.ticket.dto.EventDTO;
import com.berat.ticket.entity.Event;
import com.berat.ticket.entity.EventState;
import com.berat.ticket.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public List<EventDTO> getUpcomingEvents() {
        List<Event> events = eventRepository.findByState(EventState.AVAILABLE);
        
        return events.stream()
            .map(event -> new EventDTO(
                event.getId(),
                event.getName(),
                event.getPlannedStartTime(),
                event.getState().toString(),
                event.getSeatLayout() != null ? event.getSeatLayout().getName() : null
            ))
            .collect(Collectors.toList());
    }
    

}
