package com.bolt.earth.assignment.eventticketingsystem.controller;

import com.bolt.earth.assignment.eventticketingsystem.exception.ErrorResponse;
import com.bolt.earth.assignment.eventticketingsystem.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchases")
@Log4j2
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Operation(summary = "It purchases a ticket for an event based on event id and customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/text",
                    schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PutMapping("/purchaseTickets")
    public ResponseEntity<?> purchaseTickets(@RequestParam Long eventId, @RequestParam Long customerId) {
        log.info("Initiating the purchase of a ticket for event "+eventId+" and customer "+customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.purchaseTickets(eventId, customerId));
    }
}
