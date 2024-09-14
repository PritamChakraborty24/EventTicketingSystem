package com.bolt.earth.assignment.eventticketingsystem.controller;

import com.bolt.earth.assignment.eventticketingsystem.model.Customer;
import com.bolt.earth.assignment.eventticketingsystem.service.CustomerService;
import com.bolt.earth.assignment.eventticketingsystem.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PutMapping("/purchaseTickets")
    public ResponseEntity<?> purchaseTickets(@RequestParam Long eventId, @RequestParam Long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.purchaseTickets(eventId, customerId));
    }
}
