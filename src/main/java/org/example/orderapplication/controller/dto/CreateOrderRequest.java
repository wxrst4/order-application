package org.example.orderapplication.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateOrderRequest(
        @NotBlank(message = "Название продукат обязательное поле.")
        String productName,

        @Positive
        @NotNull(message = "Цена обязательное поле.")
        BigDecimal price,

        @Positive(message = "Количество должно быть положительным числом.")
        @Min(value = 1, message = "Количество должно быть минимум 1.")
        int quantity
) {
}
