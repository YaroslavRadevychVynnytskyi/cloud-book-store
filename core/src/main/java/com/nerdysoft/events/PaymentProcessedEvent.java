package com.nerdysoft.events;

import lombok.Data;

@Data
public class PaymentProcessedEvent {
    private final String orderId;
    private final String paymentId;
}