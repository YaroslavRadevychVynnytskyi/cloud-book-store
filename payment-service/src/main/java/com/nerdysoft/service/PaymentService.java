package com.nerdysoft.service;

import com.nerdysoft.axon.core.data.PaymentEntity;
import com.nerdysoft.events.PaymentProcessedEvent;
import com.nerdysoft.repo.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public void createPayment(PaymentProcessedEvent paymentEvent) {
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(paymentEvent, paymentEntity);

        paymentRepository.save(paymentEntity);
    }
}
