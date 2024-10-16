package com.example.ordermanagement.services;

import com.example.ordermanagement.domain.OrderDTO;
import com.example.ordermanagement.domain.OrderItemDTO;
import com.example.ordermanagement.entities.Order;
import com.example.ordermanagement.entities.OrderItem;

public interface MapperService {
    OrderDTO orderToOrderDTO(Order order);
    Order orderDTOToOrder(OrderDTO orderDTO);
    OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);
    OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO, Order order);


}

