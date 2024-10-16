package com.example.ordermanagement.services;

import com.example.ordermanagement.domain.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getAllOrders();
    OrderDTO updateOrderStatus(Long id, String newStatus);
    void deleteOrder(Long id);
}
