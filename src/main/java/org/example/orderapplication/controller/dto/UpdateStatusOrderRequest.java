package org.example.orderapplication.controller.dto;

import jakarta.validation.constraints.NotNull;
import org.example.orderapplication.model.Status;

public record UpdateStatusOrderRequest(
        @NotNull(message = "Статус обязателен.")
        Status status
) {
}
