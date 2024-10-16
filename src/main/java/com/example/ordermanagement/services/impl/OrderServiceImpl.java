package com.example.ordermanagement.services.impl;

import com.example.ordermanagement.domain.OrderDTO;
import com.example.ordermanagement.domain.OrderItemDTO;
import com.example.ordermanagement.entities.Order;
import com.example.ordermanagement.entities.OrderItem;
import com.example.ordermanagement.exceptions.OrderNotFoundException;
import com.example.ordermanagement.repositories.OrderItemRepository;
import com.example.ordermanagement.repositories.OrderRepository;
import com.example.ordermanagement.services.MapperService;
import com.example.ordermanagement.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MapperService mapperService;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = mapperService.orderDTOToOrder(orderDTO);
        Order savedOrder = orderRepository.saveAndFlush(order);

        List<OrderItem> orderItemList  = new ArrayList<>();

        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItems()) {
            orderItemList.add(mapperService.orderItemDTOToOrderItem(orderItemDTO, savedOrder));
        }

        List<OrderItem> savedList =  orderItemRepository.saveAll(orderItemList);
        savedOrder.setOrderItems(savedList);
        return mapperService.orderToOrderDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido no encontrado con ID: " + id));

        order.setOrderItems(orderItemRepository.findByPedido_Id(order.getId()));
        return convertToDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> mapperService.orderToOrderDTO(order))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrderStatus(Long id, String newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido no encontrado con ID: " + id));
        order.setOrderStatus(Order.OrderStatus.valueOf(newStatus));
        Order updatedOrder = orderRepository.save(order);

        return convertToDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setOrderStatus(String.valueOf(order.getOrderStatus()));

        List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
                .map(item -> new OrderItemDTO(item.getProductId(), item.getQuantity()))
                .collect(Collectors.toList());
        orderDTO.setOrderItems(orderItemDTOs);

        return orderDTO;
    }
}