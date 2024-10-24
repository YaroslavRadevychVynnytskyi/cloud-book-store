package com.application.command;

import com.application.core.data.ProductLookupEntity;
import com.application.core.events.ProductCreatedEvent;
import com.application.core.repository.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductLookupEventsHandler {
    private final ProductLookupRepository productLookupRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductLookupEntity productLookupEntity = new ProductLookupEntity(event.getProductId(), event.getTitle());

        productLookupRepository.save(productLookupEntity);
    }
}
