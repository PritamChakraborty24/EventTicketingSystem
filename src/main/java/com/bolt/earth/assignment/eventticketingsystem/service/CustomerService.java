package com.bolt.earth.assignment.eventticketingsystem.service;

import com.bolt.earth.assignment.eventticketingsystem.exception.CustomServiceException;
import com.bolt.earth.assignment.eventticketingsystem.model.Customer;
import com.bolt.earth.assignment.eventticketingsystem.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) {
        log.info("Registering a customer to the application");
        return customerRepository.save(customer);
    }

    public Customer findCustomerDetailsById(Long customerId) {
        log.info("Fetching customer details based on customerId");
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomServiceException("No Such Customer Found"));
    }

    public List<Customer> findAllCustomerDetails() {
        log.info("Fetching all customer details");
        List<Customer> customerList = customerRepository.findAll();
        if(customerList.isEmpty()){
            throw new CustomServiceException("No customers found in the system!");
        }
        return customerList;
    }
}
