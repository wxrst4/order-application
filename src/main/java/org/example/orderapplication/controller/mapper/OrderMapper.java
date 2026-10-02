package org.example.orderapplication.controller.mapper;

import org.example.orderapplication.controller.dto.OrderResponse;
import org.example.orderapplication.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProductName(),
                order.getPrice(),
                order.getQuantity(),
                order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }

    public List<OrderResponse> toResponse(List<Order> orders) {
        return orders.stream()
                .map(this::toResponse)
                .toList();
    }
}
