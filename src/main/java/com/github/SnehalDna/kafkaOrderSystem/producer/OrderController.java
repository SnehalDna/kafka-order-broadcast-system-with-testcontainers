package com.github.SnehalDna.kafkaOrderSystem.producer;

import  com.github.SnehalDna.kafkaOrderSystem.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer; // Injected Producer

    @PostMapping("/place")
    public String placeOrder(@RequestBody Order order) {
        orderProducer.sendOrder(order);
        return "Order " + order.getOrderId() + " placed successfully!";
    }
}