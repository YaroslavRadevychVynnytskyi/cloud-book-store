package com.application.core.events;

import com.application.core.data.ProductEntity;
import com.application.core.repository.ProductLookupRepository;
import com.application.core.repository.ProductRepository;
import com.nerdysoft.events.ProductReservedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductEventsHandler {
    private final ProductRepository productRepository;
    private final ProductLookupRepository productLookupRepository;

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        try {
            productRepository.save(productEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (true) throw new Exception("Forcing exception in the Event Handler class");
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        ProductEntity productEntity = productRepository.findByProductId(productReservedEvent.getProductId()).get();
        productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());

        productRepository.save(productEntity);
    }
}
