package com.bolt.earth.assignment.eventticketingsystem.service;

import com.bolt.earth.assignment.eventticketingsystem.exception.ServiceException;
import com.bolt.earth.assignment.eventticketingsystem.model.Customer;
import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import com.bolt.earth.assignment.eventticketingsystem.model.Purchase;
import com.bolt.earth.assignment.eventticketingsystem.model.Ticket;
import com.bolt.earth.assignment.eventticketingsystem.repository.CustomerRepository;
import com.bolt.earth.assignment.eventticketingsystem.repository.EventRepository;
import com.bolt.earth.assignment.eventticketingsystem.repository.PurchaseRepository;
import com.bolt.earth.assignment.eventticketingsystem.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer;
        if(optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        } else {
            throw new ServiceException("No Such customer found!");
        }
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            if (event.getTicketsAvailable() > 0) {

                final Ticket ticket = Ticket.builder().event(event).build();

                ticketRepository.save(ticket);
                event.setTicketsAvailable(event.getTicketsAvailable() - 1);

//                List<Ticket> ticketSet = event.getTickets();
//
//                if(ticketSet == null) {
//                    ticketSet = new CopyOnWriteArrayList<>();
//                }
//
//                ticketSet.add(ticket);
//
//                event.setTickets(ticketSet);

                eventRepository.save(event);

                final Purchase purchase = Purchase.builder().ticket(ticket).customer(customer).build();
                purchaseRepository.save(purchase);

                customer.setPurchase(purchase);
                customerRepository.save(customer);

                return "Ticket purchased successfully.";
            } else {
                throw new ServiceException("Sorry, no tickets are available!");
            }
        } else {
            throw new ServiceException("No such event found!");
        }
    }
}
