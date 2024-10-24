package com.nerdysoft.core.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class ReserveProductCommand {
    @TargetAggregateIdentifier
    private String productId;
    private int quantity;
    private String orderId;
    private String userId;
}
