package com.nerdysoft.axon.command;

import com.nerdysoft.dto.OrderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
@AllArgsConstructor
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    public final String orderId;
    private final String userId;
    private final String productId;
    private final int quantity;
    private final String addressId;
    private final OrderStatus orderStatus;

    public enum OrderStatus {
        CREATED, APPROVED, REJECTED
    }

    public CreateOrderCommand(String orderId, OrderRequestDto requestDto) {
        this.orderId = orderId;
        userId = "27b95829-4f3f-4ddf-8983-151ba010e35b";
        productId = requestDto.getProductId();
        quantity = requestDto.getQuantity();
        addressId = requestDto.getAddressId();
        orderStatus = OrderStatus.CREATED;
    }
}
