package org.example.orderapplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.orderapplication.controller.common.Response;
import org.example.orderapplication.controller.dto.CreateOrderRequest;
import org.example.orderapplication.controller.dto.OrderResponse;
import org.example.orderapplication.controller.dto.UpdateStatusOrderRequest;
import org.example.orderapplication.controller.mapper.OrderMapper;
import org.example.orderapplication.model.Order;
import org.example.orderapplication.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public Response<OrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request
    ) {

        Order order = orderService.createOrder(request);

        return Response.createdOf(orderMapper.toResponse(order));
    }

    @GetMapping("/{orderId}")
    public Response<OrderResponse> getOrderById(@PathVariable UUID orderId) {
        Order order = orderService.getOrderById(orderId);

        return Response.of(orderMapper.toResponse(order));
    }

    @GetMapping
    public Response<List<OrderResponse>> getAllOrders(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size
    ) {
        List<Order> orders = orderService.getAllOrders(page, size);

        return Response.of(orderMapper.toResponse(orders));
    }

    @PatchMapping("/{orderId}/status")
    public Response<OrderResponse> updateStatusOrderById(
            @PathVariable UUID orderId,
            @Valid @RequestBody UpdateStatusOrderRequest request
    ) {
        Order order = orderService.updateStatusById(orderId, request);

        return Response.createdOf(orderMapper.toResponse(order));
    }

    @PostMapping("/{orderId}/cancel")
    public Response<Void> cancelOrderById(
            @PathVariable UUID orderId,
            @Valid @RequestBody UpdateStatusOrderRequest request
    ) {
        orderService.updateStatusById(orderId, request);

        return Response.noContent();
    }
}
