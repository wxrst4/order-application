package org.example.orderapplication.controller.dto;

import org.example.orderapplication.model.Status;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        String productName,
        BigDecimal price,
        Integer quantity,
        BigDecimal totalPrice,
        Status status,
        Instant createdAt,
        Instant updateAt
) {
}
