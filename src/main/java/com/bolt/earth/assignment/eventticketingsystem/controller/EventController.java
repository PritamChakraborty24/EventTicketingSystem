package com.bolt.earth.assignment.eventticketingsystem.controller;

import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import com.bolt.earth.assignment.eventticketingsystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/createEvent")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(event));
    }

    @GetMapping("/findEventDetailsById/{eventId}")
    public ResponseEntity<?> findEventDetailsById(@PathVariable Long eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findEventDetailsById(eventId));
    }

    @GetMapping("/findAllEventDetails")
    public ResponseEntity<?> findAllEventDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findAllEventDetails());
    }
}
