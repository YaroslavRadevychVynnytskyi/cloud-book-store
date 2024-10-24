package com.nerdysoft.axon.core.data;

import com.nerdysoft.axon.command.CreateOrderCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
    @Id
    @Column(unique = true)
    public String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    @Enumerated
    private CreateOrderCommand.OrderStatus orderStatus;
}
