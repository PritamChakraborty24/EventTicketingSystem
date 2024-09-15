package com.bolt.earth.assignment.eventticketingsystem.controller;

import com.bolt.earth.assignment.eventticketingsystem.exception.ErrorResponse;
import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import com.bolt.earth.assignment.eventticketingsystem.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@Log4j2
public class EventController {

    @Autowired
    private EventService eventService;

    @Operation(summary = "It creates a new event on the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/createEvent")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        log.info("Adding an event to the system");
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(event));
    }

    @Operation(summary = "It fetches a particular event based on its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Event.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/findEventDetailsById/{eventId}")
    public ResponseEntity<?> findEventDetailsById(@PathVariable Long eventId) {
        log.info("Fetching event based on event id");
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findEventDetailsById(eventId));
    }

    @Operation(summary = "It fetches all the events present in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Event.class)))}),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/findAllEventDetails")
    public ResponseEntity<?> findAllEventDetails() {
        log.info("Fetching all available events on the system");
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findAllEventDetails());
    }

    @Operation(summary = "It fetches available number of tickets for an event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/text",
                    schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/findAvailableTicketsForAnEvent/{eventId}")
    public ResponseEntity<?> findAvailableTicketsForAnEvent(@PathVariable Long eventId) {
        log.info("Fetching the available tickets for an event");
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findAvailableTicketsForAnEvent(eventId));
    }
}
