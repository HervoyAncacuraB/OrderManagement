package com.example.ordermanagement.controllers;


import com.example.ordermanagement.domain.OrderDTO;
import com.example.ordermanagement.entities.Order;
import com.example.ordermanagement.repositories.OrderRepository;
import com.example.ordermanagement.services.impl.MapperServiceImpl;
import com.example.ordermanagement.services.impl.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MapperServiceImpl mapperService;

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        log.info("orderDTO,  {}", orderDTO);
        OrderDTO savedOrderDTO = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderDTO);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orderDTOList = orderService.getAllOrders();
    return ResponseEntity.status(HttpStatus.OK).body(orderDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return orderRepository.findById(id)
                .map(order -> {
            order.setOrderStatus(Order.OrderStatus.valueOf(orderDTO.getOrderStatus()));

            Order updatedOrder = orderRepository.save(order);
            return ResponseEntity.ok(mapperService.orderToOrderDTO(updatedOrder));
        })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}