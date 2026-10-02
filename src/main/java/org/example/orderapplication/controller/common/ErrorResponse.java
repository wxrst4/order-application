package org.example.orderapplication.controller.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Instant timestamp;

    public static ErrorResponse ofResponse(String message) {
        return new ErrorResponse(message, Instant.now());
    }
}
