package com.bolt.earth.assignment.eventticketingsystem.service;

import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import com.bolt.earth.assignment.eventticketingsystem.repository.EventRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event findEventDetailsById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new ServiceException("No Such event found!"));
    }

    public List<Event> findAllEventDetails() {
        return eventRepository.findAll();
    }
}
