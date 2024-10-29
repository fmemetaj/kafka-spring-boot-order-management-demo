package org.fmemetaj.kafkaspringbootordermanagementdemo.kafka;

import org.fmemetaj.kafkaspringbootordermanagementdemo.entity.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(topics = "order_events", groupId = "order_group",
            containerFactory = "orderKafkaListenerContainerFactory")
    public void consumeOrderEvent(Order order) {
        System.out.println("Received Order Event: " + order);
        // Simulate processing (e.g., updating inventory, sending notifications)
    }
}
