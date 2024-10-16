package com.example.ordermanagement.services.impl;

import com.example.ordermanagement.domain.OrderDTO;
import com.example.ordermanagement.domain.OrderItemDTO;
import com.example.ordermanagement.entities.Order;
import com.example.ordermanagement.entities.OrderItem;
import com.example.ordermanagement.services.MapperService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperServiceImpl implements MapperService {
    @Override
    public Order orderDTOToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setOrderStatus(Order.OrderStatus.valueOf(orderDTO.getOrderStatus()));
        return order;
    }

    @Override
    public OrderDTO orderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setOrderStatus(String.valueOf(order.getOrderStatus()));
        List<OrderItemDTO> list = new ArrayList<>();

        for (OrderItem oderItem : order.getOrderItems()) {
            list.add(orderItemToOrderItemDTO(oderItem));
        }

        orderDTO.setOrderItems(list);
        return orderDTO;
    }

    @Override
    public OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(orderItem.getProductId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        return orderItemDTO;
    }

    @Override
    public OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO, Order order) {
        OrderItem orderItem = new OrderItem();
        Order orderToSave = new Order();
        orderToSave.setId(order.getId());
        orderItem.setPedido(orderToSave);
        orderItem.setProductId(orderItemDTO.getProductId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        return orderItem;
    }
}