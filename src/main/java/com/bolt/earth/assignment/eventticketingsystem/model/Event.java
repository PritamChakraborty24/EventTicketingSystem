package com.bolt.earth.assignment.eventticketingsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private String eventDate;
    private int totalTickets;
    private int ticketsAvailable;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;
}
