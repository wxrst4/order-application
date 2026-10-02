package org.example.orderapplication.service;

import org.example.orderapplication.controller.dto.CreateOrderRequest;
import org.example.orderapplication.exception.OrderNotFoundException;
import org.example.orderapplication.model.Order;
import org.example.orderapplication.model.Status;
import org.example.orderapplication.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldReturnOrderWhenOrderExists() {
        UUID orderId = UUID.randomUUID();

        Order order = new Order();
        order.setId(orderId);
        order.setProductName("productName");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order res = orderService.getOrderById(orderId);
        assertEquals(order.getId(), res.getId());
        assertEquals(order.getProductName(), res.getProductName());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void shouldThrowExceptionWhenOrderDoesNotExist() {
        UUID orderId = UUID.randomUUID();

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(orderId));
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void shouldCreateOrder() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(
                "MacBook",
                new BigDecimal("100000"),
                1
        );

        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        Order res = orderService.createOrder(createOrderRequest);

        assertEquals(Status.NEW, res.getStatus());
        assertEquals(createOrderRequest.productName(), res.getProductName());
        assertEquals(createOrderRequest.quantity(), res.getQuantity());
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
