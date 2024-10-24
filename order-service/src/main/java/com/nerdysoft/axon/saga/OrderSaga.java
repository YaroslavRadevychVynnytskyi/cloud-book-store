package com.nerdysoft.axon.saga;

import com.nerdysoft.axon.core.events.OrderCreatedEvent;
import com.nerdysoft.core.commands.ReserveProductCommand;
import com.nerdysoft.core.model.User;
import com.nerdysoft.core.query.FetchUserPaymentDetailsQuery;
import com.nerdysoft.events.ProductReservedEvent;
import javax.annotation.Nonnull;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class OrderSaga {
    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSaga.class);

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .productId(orderCreatedEvent.getProductId())
                .quantity(orderCreatedEvent.getQuantity())
                .orderId(orderCreatedEvent.getOrderId())
                .userId(orderCreatedEvent.getUserId())
                .build();

        LOGGER.info("OrderCreateEvent handled for orderId: " + reserveProductCommand.getOrderId()
                + " and productId: " + reserveProductCommand.getProductId());

        commandGateway.send(reserveProductCommand, new CommandCallback<ReserveProductCommand, Object>() {
            @Override
            public void onResult(@Nonnull CommandMessage<? extends ReserveProductCommand> commandMessage, @Nonnull CommandResultMessage<?> commandResultMessage) {
                if (commandResultMessage.isExceptional()) {
                    // Start a compensating transaction
                }
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
        //Process user payment
        LOGGER.info("ProductReservedEvent is called for productId: " + productReservedEvent.getProductId()
                + " and orderId: " + productReservedEvent.getOrderId());

        FetchUserPaymentDetailsQuery query = new FetchUserPaymentDetailsQuery(productReservedEvent.getUserId());

        User userPaymentDetails = null;

        try {
            userPaymentDetails = queryGateway.query(query, ResponseTypes.instanceOf(User.class)).join();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            //Start a compensation transaction
        }

        if (userPaymentDetails == null) {
            //Start a compensation transaction
            return;
        }

        LOGGER.info("Successfully fetched user payment details for user " + userPaymentDetails.getFirstName());
    }
}
