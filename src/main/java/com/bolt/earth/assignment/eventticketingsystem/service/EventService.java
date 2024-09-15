package com.bolt.earth.assignment.eventticketingsystem.service;

import com.bolt.earth.assignment.eventticketingsystem.exception.CustomServiceException;
import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import com.bolt.earth.assignment.eventticketingsystem.repository.EventRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        log.info("Saving an event to the system");
        return eventRepository.save(event);
    }

    public Event findEventDetailsById(Long eventId) {
        log.info("Fetching details of an event from the system");
        return eventRepository.findById(eventId).orElseThrow(() -> new CustomServiceException("No Such event found!"));
    }

    public List<Event> findAllEventDetails() {
        log.info("Fetching all event details");
        List<Event> eventList = eventRepository.findAll();
        if(eventList.isEmpty()) {
            throw new CustomServiceException("No events found in the system!");
        }
        return eventList;
    }

    public String findAvailableTicketsForAnEvent(Long eventId) {
        log.info("Fetching available tickets for an event");
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new CustomServiceException("No Such event found!"));
        return "Total tickets available for " + event.getEventName() + " is  "+ event.getTicketsAvailable();
    }
}
