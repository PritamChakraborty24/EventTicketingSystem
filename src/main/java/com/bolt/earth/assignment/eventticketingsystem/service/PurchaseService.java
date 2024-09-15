package com.bolt.earth.assignment.eventticketingsystem.service;

import com.bolt.earth.assignment.eventticketingsystem.exception.CustomServiceException;
import com.bolt.earth.assignment.eventticketingsystem.model.Customer;
import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import com.bolt.earth.assignment.eventticketingsystem.model.Purchase;
import com.bolt.earth.assignment.eventticketingsystem.model.Ticket;
import com.bolt.earth.assignment.eventticketingsystem.repository.CustomerRepository;
import com.bolt.earth.assignment.eventticketingsystem.repository.EventRepository;
import com.bolt.earth.assignment.eventticketingsystem.repository.PurchaseRepository;
import com.bolt.earth.assignment.eventticketingsystem.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Log4j2
public class PurchaseService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public synchronized String purchaseTickets(final Long eventId, final Long customerId) {
        log.info("Fetching customer details to map the purchase");
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer;

        if(optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        } else {
            throw new CustomServiceException("No Such customer found!");
        }

        log.info("Fetching the event details to map the purchase");
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();

            log.info("Checking for ticket availibility");
            if (event.getTicketsAvailable() > 0) {

                final Ticket ticket = Ticket.builder().event(event).build();

                ticketRepository.save(ticket);

                log.info("Decrementing availibility count by 1");
                event.setTicketsAvailable(event.getTicketsAvailable() - 1);

                List<Ticket> ticketList = event.getTickets();

                if(ticketList == null) {
                    ticketList = new CopyOnWriteArrayList<>();
                }

                ticketList.add(ticket);

                log.info("Mapping the updated ticket availibility count to the event");
                event.setTickets(ticketList);

                eventRepository.save(event);

                final Purchase purchase = Purchase.builder().ticket(ticket).customer(customer).build();
                purchaseRepository.save(purchase);

                List<Purchase> purchases = customer.getPurchase();
                if(purchases == null) {
                    purchases = new CopyOnWriteArrayList<>();
                }
                purchases.add(purchase);

                log.info("Mapping the purchase to the customer");
                customer.setPurchase(purchases);
                customerRepository.save(customer);

                log.info("Ticket purchase is successful");
                return "Ticket purchased successfully.";
            } else {
                log.info("No tickets are available");
                throw new CustomServiceException("Sorry, no tickets are available!");
            }
        } else {
            throw new CustomServiceException("No such event found!");
        }
    }
}
