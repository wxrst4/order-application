package org.example.orderapplication.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderapplication.controller.dto.CreateOrderRequest;
import org.example.orderapplication.controller.dto.UpdateStatusOrderRequest;
import org.example.orderapplication.exception.OrderNotFoundException;
import org.example.orderapplication.model.Order;
import org.example.orderapplication.model.Status;
import org.example.orderapplication.repository.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        Order order = new Order(
                request.productName(),
                request.price(),
                request.quantity()
        );

        return orderRepository.save(order);
    }

    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

    public List<Order> getAllOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size)).stream().toList();
    }

    @Transactional
    public Order updateStatusById(UUID id, UpdateStatusOrderRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        if (order.getStatus().equals(Status.COMPLETED) || order.getStatus().equals(Status.CANCELLED)) {
            throw new IllegalStateException(
                    "Невозможно изменить статус заказа из " + order.getStatus() + " в " + request.status()
            );
        }

        order.setStatus(request.status());

        return order;
    }
}
