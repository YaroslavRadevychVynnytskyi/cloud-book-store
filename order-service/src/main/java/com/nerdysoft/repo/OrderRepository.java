package com.nerdysoft.repo;

import com.nerdysoft.axon.core.data.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
