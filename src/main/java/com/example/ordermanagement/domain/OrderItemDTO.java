package com.example.ordermanagement.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemDTO {

    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;

    public OrderItemDTO() {}

    public OrderItemDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
