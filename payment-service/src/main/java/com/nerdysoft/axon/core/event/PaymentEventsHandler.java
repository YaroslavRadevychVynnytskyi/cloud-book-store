package com.nerdysoft.axon.core.event;

import com.nerdysoft.events.PaymentProcessedEvent;
import com.nerdysoft.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("payment-group")
@RequiredArgsConstructor
public class PaymentEventsHandler {
    private final PaymentService paymentService;

    @EventHandler
    public void on(PaymentProcessedEvent paymentEvent) {
        paymentService.createPayment(paymentEvent);
    }
}
