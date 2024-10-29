package org.fmemetaj.kafkaspringbootordermanagementdemo.kafka;

import org.fmemetaj.kafkaspringbootordermanagementdemo.entity.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final String TOPIC = "order_events";

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(Order order) {
        kafkaTemplate.send(TOPIC, order);
    }
}
