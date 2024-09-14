package com.bolt.earth.assignment.eventticketingsystem.repository;

import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
