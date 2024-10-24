package com.nerdysoft.service;

import com.nerdysoft.axon.core.data.OrderEntity;
import com.nerdysoft.axon.core.events.OrderApprovedEvent;
import com.nerdysoft.axon.core.events.OrderCreatedEvent;
import com.nerdysoft.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.RepositorySpanFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrder(OrderCreatedEvent orderCreatedEvent) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderCreatedEvent, orderEntity);

        orderRepository.save(orderEntity);
    }

    public void approveOrder(OrderApprovedEvent orderApprovedEvent) {
        OrderEntity order = orderRepository.findByOrderId(orderApprovedEvent.getOrderId()).orElseThrow();
        order.setOrderStatus(orderApprovedEvent.getOrderStatus());

        orderRepository.save(order);
    }
}
