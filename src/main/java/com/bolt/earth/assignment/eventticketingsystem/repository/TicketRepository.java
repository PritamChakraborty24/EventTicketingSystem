package com.bolt.earth.assignment.eventticketingsystem.repository;

import com.bolt.earth.assignment.eventticketingsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
