package com.github.SnehalDna.kafkaOrderSystem.consumer;

import com.github.SnehalDna.kafkaOrderSystem.model.Order;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {
    @Retryable(
            include = { Exception.class }, // exceptions to retry
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2.0)
    )
    @KafkaListener(topics = "order-topic", groupId = "shipping-group")
    public void processOrder(Order order) {
            if (order.getPrice() < 0) {
                throw new RuntimeException("Invalid Price!"); // This triggers a retry
            }
            System.out.println("Shipping processed: " + order.getOrderId());
        }

        @DltHandler
        public void handleDlt(Order order) {
            System.err.println("CRITICAL: Order moved to Dead Letter Topic: " + order.getOrderId());
            // Here you would save to a "Failed Orders" table for manual review
        }

}