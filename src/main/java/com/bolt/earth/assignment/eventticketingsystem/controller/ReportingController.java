package com.bolt.earth.assignment.eventticketingsystem.controller;

import com.bolt.earth.assignment.eventticketingsystem.exception.ErrorResponse;
import com.bolt.earth.assignment.eventticketingsystem.model.Event;
import com.bolt.earth.assignment.eventticketingsystem.service.ReportingService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/reports")
@Log4j2
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @Operation(summary = "It fetches the tickets sold for an event when event is passed in the path variable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/text",
                    schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/ticketsSoldForAnEvent/{eventId}")
    public ResponseEntity<?> getTicketsSoldForAnEvent(@PathVariable Long eventId) {
        log.info("Request for getting tickets for an event");
        return ResponseEntity.status(HttpStatus.OK).body(reportingService.getTicketsSoldForAnEvent(eventId));
    }

    @Operation(summary = "It fetches the revenue generated for an event when event is passed in the path variable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/text",
                    schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/revenueGeneratedForAnEvent/{eventId}")
    public ResponseEntity<?> getRevenueGeneratedForAnEvent(@PathVariable Long eventId) {
        log.info("Request for getting revenue generated for an event");
        return ResponseEntity.status(HttpStatus.OK).body(reportingService.getRevenueGeneratedForAnEvent(eventId));
    }

    @Operation(summary = "It fetches the total revenue generated for all the events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/text",
                    schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/totalRevenueGenerated")
    public ResponseEntity<?> getRevenueGeneratedForAllEvents() {
        log.info("Request for getting total revenue generated for all events");
        return ResponseEntity.status(HttpStatus.OK).body(reportingService.getRevenueGeneratedForAllEvents());
    }

    @Operation(summary = "It fetches the total number of tickets purchased for all the events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/text",
                    schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/totalTicketsSold")
    public ResponseEntity<?> getTotalTicketsSoldForAllEvents() {
        log.info("Request for getting total tickets sold for all events");
        return ResponseEntity.status(HttpStatus.OK).body(reportingService.getTotalTicketsSoldForAllEvents());
    }

}
