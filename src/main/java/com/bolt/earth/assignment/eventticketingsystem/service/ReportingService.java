package com.bolt.earth.assignment.eventticketingsystem.service;

import com.bolt.earth.assignment.eventticketingsystem.exception.CustomServiceException;
import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import com.bolt.earth.assignment.eventticketingsystem.repository.EventRepository;
import com.bolt.earth.assignment.eventticketingsystem.repository.PurchaseRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ReportingService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public String getTicketsSoldForAnEvent(Long eventId) {
        log.info("Calculating the number of tickets sold for an event");
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new CustomServiceException("No Such event found!"));
        return "Tickets sold for  "+ event.getEventName() + " is " + (event.getTotalTickets() - event.getTicketsAvailable());
    }

    public String getRevenueGeneratedForAnEvent(Long eventId) {
        log.info("Calculating the revenue generated for an event");
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new CustomServiceException("No Such event found!"));
        return "Revenue generated for "+ event.getEventName() + " is " + (double)((event.getTotalTickets() - event.getTicketsAvailable()) * event.getTicketCost());

    }

    public String getRevenueGeneratedForAllEvents() {
        log.info("Calculating the revenue generated for all events");
        List<Event> eventLists = eventRepository.findAll();
        double totalRevenue = 0;
        if(!eventLists.isEmpty()) {
            for(Event event : eventLists) {
                totalRevenue += ((event.getTotalTickets() - event.getTicketsAvailable()) * event.getTicketCost());
            }
        } else {
            throw new CustomServiceException("No events are found!");
        }
        return "Total revenue generated is " + totalRevenue;
    }

    public String getTotalTicketsSoldForAllEvents() {
        log.info("Calculating the total tickets sold for all events");
        long totalTicketsSold = purchaseRepository.count();
        if(totalTicketsSold == 0)
            return "No tickets are sold yet";
        else
            return "Total no. of tickets sold for all events is " + totalTicketsSold;
    }
}
