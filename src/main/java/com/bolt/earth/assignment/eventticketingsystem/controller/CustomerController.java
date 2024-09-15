package com.bolt.earth.assignment.eventticketingsystem.controller;

import com.bolt.earth.assignment.eventticketingsystem.exception.ErrorResponse;
import com.bolt.earth.assignment.eventticketingsystem.model.Customer;
import com.bolt.earth.assignment.eventticketingsystem.service.CustomerService;
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
@RequestMapping("/customers")
@Log4j2
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "It registers a new customer on the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/registerCustomer")
    public ResponseEntity<?> registerCustomers(@RequestBody Customer customer) {
        log.info("Registering customers into the system");
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.registerCustomer(customer));
    }

    @Operation(summary = "It fetches a particular customer based on its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/findCustomerDetailsById/{customerId}")
    public ResponseEntity<?> findCustomerDetailsById(@PathVariable Long customerId) {
        log.info("Fetching customer details based on customer id");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerDetailsById(customerId));
    }

    @Operation(summary = "It fetches all the customers present in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Customer.class)))}),
            @ApiResponse(responseCode = "500", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/findAllCustomerDetails")
    public ResponseEntity<?> findAllCustomerDetails() {
        log.info("Fetching all customer details");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomerDetails());
    }
}
