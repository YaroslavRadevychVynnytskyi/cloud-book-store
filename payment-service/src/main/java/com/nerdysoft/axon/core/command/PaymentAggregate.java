package com.nerdysoft.axon.core.command;

import com.nerdysoft.core.commands.ProcessPaymentCommand;
import com.nerdysoft.events.PaymentProcessedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class PaymentAggregate {
    @AggregateIdentifier
    private String paymentId;
    private String orderId;

    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand) {
        if (processPaymentCommand.getOrderId().isBlank()) {
            throw new IllegalArgumentException("Invalid orderId");
        }

        if (processPaymentCommand.getPaymentId().isBlank()) {
            throw new IllegalArgumentException("Invalid paymentId");
        }

        if (processPaymentCommand.getPaymentDetails() == null) {
            throw new IllegalArgumentException("Invalid paymentDetails");
        }

        PaymentProcessedEvent paymentProcessedEvent =
                new PaymentProcessedEvent(processPaymentCommand.getOrderId(), processPaymentCommand.getPaymentId());

        AggregateLifecycle.apply(paymentProcessedEvent);
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        this.paymentId = paymentProcessedEvent.getPaymentId();
        this.orderId = paymentProcessedEvent.getOrderId();
    }
}
