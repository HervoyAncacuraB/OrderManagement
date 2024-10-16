package com.example.ordermanagement.domain;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    @NotNull
    private Long id;
    @NotNull
    private String customerId;
    @NotNull
    private String orderStatus;
    @NotNull
    private List<OrderItemDTO> orderItems;

}