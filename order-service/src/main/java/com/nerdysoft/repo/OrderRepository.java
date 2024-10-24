package com.nerdysoft.repo;

import com.nerdysoft.axon.core.data.OrderEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    Optional<OrderEntity> findByOrderId(String orderId);
}
