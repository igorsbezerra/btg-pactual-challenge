package dev.igor.btgpactual.listener;

import dev.igor.btgpactual.listener.dto.OrderCreatedEvent;
import dev.igor.btgpactual.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static dev.igor.btgpactual.config.RabbitMQConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreateListener {
    private static final Logger log = LoggerFactory.getLogger(OrderCreateListener.class);
    private final OrderService service;

    public OrderCreateListener(OrderService service) {
        this.service = service;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        service.save(message.getPayload());
    }
}
