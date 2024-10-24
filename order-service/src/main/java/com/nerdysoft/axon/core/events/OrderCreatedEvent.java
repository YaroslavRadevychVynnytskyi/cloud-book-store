package com.nerdysoft.axon.core.events;

import com.nerdysoft.axon.command.CreateOrderCommand;
import lombok.Data;

@Data
public class OrderCreatedEvent {
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private CreateOrderCommand.OrderStatus orderStatus;
}
