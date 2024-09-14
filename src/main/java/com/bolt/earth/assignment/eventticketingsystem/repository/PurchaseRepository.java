package com.bolt.earth.assignment.eventticketingsystem.repository;

import com.bolt.earth.assignment.eventticketingsystem.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
