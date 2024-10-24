package com.nerdysoft.repo;

import com.nerdysoft.axon.core.data.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
}
