package com.nerdysoft.service;

import com.nerdysoft.axon.core.data.OrderEntity;
import com.nerdysoft.axon.core.events.OrderCreatedEvent;
import com.nerdysoft.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
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
}
