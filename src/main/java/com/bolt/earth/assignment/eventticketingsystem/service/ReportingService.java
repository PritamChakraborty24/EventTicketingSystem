package com.bolt.earth.assignment.eventticketingsystem.service;

import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import com.bolt.earth.assignment.eventticketingsystem.repository.EventRepository;
import com.bolt.earth.assignment.eventticketingsystem.repository.PurchaseRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public String getTicketsSoldForAnEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ServiceException("No Such event found!"));
        return "Tickets sold for  "+ event.getEventName() + " is " + (event.getTotalTickets() - event.getTicketsAvailable());
    }

    public String getRevenueGeneratedForAnEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ServiceException("No Such event found!"));
        return "Revenue generated for "+ event.getEventName() + " is " + (double)((event.getTotalTickets() - event.getTicketsAvailable()) * event.getTicketCost());

    }

    public String getRevenueGeneratedForAllEvents() {
        List<Event> eventLists = eventRepository.findAll();
        double totalRevenue = 0;
        if(!eventLists.isEmpty()) {
            for(Event event : eventLists) {
                totalRevenue += ((event.getTotalTickets() - event.getTicketsAvailable()) * event.getTicketCost());
            }
        } else {
            throw new ServiceException("No events are found!");
        }
        return "Total revenue generated is " + totalRevenue;
    }

    public String getTotalTicketsSoldForAllEvents() {
        return "Total no. of tickets sold for all events is " + purchaseRepository.count();
    }
}
