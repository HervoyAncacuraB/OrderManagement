package com.example.ordermanagement.repositories;

import com.example.ordermanagement.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByPedido_Id(Long orderId);
}
