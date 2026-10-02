package org.example.orderapplication.exception;

public class OrderStatusUpdateException extends RuntimeException {
    public OrderStatusUpdateException() {
        super("Order status update exception.");
    }
}
