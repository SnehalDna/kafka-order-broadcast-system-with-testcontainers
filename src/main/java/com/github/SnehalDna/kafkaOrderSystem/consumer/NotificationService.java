package com.github.SnehalDna.kafkaOrderSystem.consumer;

import com.github.SnehalDna.kafkaOrderSystem.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static reactor.netty.http.HttpConnectionLiveness.log;

@Service
public class NotificationService {

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void sendEmail(Order order) {
        if (order.getProduct() == null) {
            throw new RuntimeException("Product out of stock!");
        }
        log.info("📧 NOTIFICATION: Sending confirmation email for: " + order.getProduct());
    }
}