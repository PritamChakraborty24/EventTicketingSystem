package com.bolt.earth.assignment.eventticketingsystem.service;

import com.bolt.earth.assignment.eventticketingsystem.model.Customer;
import com.bolt.earth.assignment.eventticketingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public Customer findCustomerDetailsById(Long customerId) {
        return customerRepository.getById(customerId);
    }

    public List<Customer> findAllCustomerDetails() {
        return customerRepository.findAll();
    }
}
