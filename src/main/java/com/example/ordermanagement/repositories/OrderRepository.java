package com.example.ordermanagement.repositories;

import com.example.ordermanagement.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}