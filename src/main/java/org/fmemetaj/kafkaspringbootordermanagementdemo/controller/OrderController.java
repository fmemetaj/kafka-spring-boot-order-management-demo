package org.fmemetaj.kafkaspringbootordermanagementdemo.controller;

import org.fmemetaj.kafkaspringbootordermanagementdemo.entity.Order;
import org.fmemetaj.kafkaspringbootordermanagementdemo.kafka.OrderProducer;
import org.fmemetaj.kafkaspringbootordermanagementdemo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderProducer orderProducer;

    public OrderController(final OrderService orderService, final OrderProducer orderProducer) {
        this.orderService = orderService;
        this.orderProducer = orderProducer;
    }

    @PostMapping("create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        orderProducer.sendOrderEvent(savedOrder);

        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("update")
    public ResponseEntity<Order> updateOrder(
            @RequestBody Order order
    ) {
        Order updatedOrder = orderService.saveOrder(order);
        orderProducer.sendOrderEvent(updatedOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
