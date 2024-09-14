package com.bolt.earth.assignment.eventticketingsystem.repository;

import com.bolt.earth.assignment.eventticketingsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
