package com.github.SnehalDna.kafkaOrderSystem.producer;

import com.github.SnehalDna.kafkaOrderSystem.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;
    private static final String TOPIC = "order-topic";

    public void sendOrder(Order order) {
        log.info("Sending order {} to Kafka topic {}", order.getOrderId(), TOPIC);
        kafkaTemplate.send(TOPIC, order.getOrderId(), order);
    }
}