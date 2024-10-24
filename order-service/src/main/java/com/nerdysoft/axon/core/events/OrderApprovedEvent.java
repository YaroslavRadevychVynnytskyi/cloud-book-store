package com.nerdysoft.axon.core.events;

import com.nerdysoft.axon.command.CreateOrderCommand;
import lombok.Value;

@Value
public class OrderApprovedEvent {
    String orderId;
    CreateOrderCommand.OrderStatus orderStatus = CreateOrderCommand.OrderStatus.APPROVED;
}
