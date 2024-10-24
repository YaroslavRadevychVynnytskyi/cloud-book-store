package com.application.axon.core.event;

import com.nerdysoft.core.details.PaymentDetails;
import com.nerdysoft.core.model.User;
import com.nerdysoft.core.query.FetchUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {
    @QueryHandler
    public User fetchUserPaymentDetails(FetchUserPaymentDetailsQuery query) {
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .cardNumber("123Card")
                .cvv("123")
                .name("YAROSLAV RADEVYCH-VYNNYTSKYI")
                .validUntilMonth(12)
                .validUntilYear(2030)
                .build();

        return User.builder()
                .firstName("Yaroslav")
                .lastName("Radevych-Vynnytskyi")
                .userId(query.getUserId())
                .paymentDetails(paymentDetails)
                .build();
    }
}
