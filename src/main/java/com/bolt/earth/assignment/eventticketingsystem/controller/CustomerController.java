package com.bolt.earth.assignment.eventticketingsystem.controller;

import com.bolt.earth.assignment.eventticketingsystem.model.Customer;
import com.bolt.earth.assignment.eventticketingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomers(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.registerCustomer(customer));
    }

    @GetMapping("/findCustomerDetailsById/{customerId}")
    public ResponseEntity<Customer> findCustomerDetailsById(@PathVariable Long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerDetailsById(customerId));
    }

    @GetMapping("/findAllCustomerDetails")
    public ResponseEntity<List<Customer>> findAllCustomerDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomerDetails());
    }
}
