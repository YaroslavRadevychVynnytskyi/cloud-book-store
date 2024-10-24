package com.nerdysoft.axon.core.events;

import com.nerdysoft.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("order-group")
@RequiredArgsConstructor
public class OrderEventsHandler {
    private final OrderService orderService;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        orderService.createOrder(event);
    }

    @EventHandler
    public void on(OrderApprovedEvent event) {
        orderService.approveOrder(event);
    }
}
