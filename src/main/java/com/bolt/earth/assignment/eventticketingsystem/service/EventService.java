package com.bolt.earth.assignment.eventticketingsystem.service;

import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import com.bolt.earth.assignment.eventticketingsystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
}
