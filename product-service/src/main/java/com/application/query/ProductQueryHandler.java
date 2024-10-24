package com.application.query;

import com.application.core.repository.ProductRepository;
import com.application.query.dto.ProductResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {
    private final ProductRepository productRepository;

    @QueryHandler
    public List<ProductResponseDto> findProducts(FindProductsQuery findProductsQuery) {
        return productRepository.findAll().stream()
                .map(pe -> {
                    ProductResponseDto productResponseDto = new ProductResponseDto();
                    BeanUtils.copyProperties(pe, productResponseDto);
                    return productResponseDto;
                })
                .toList();
    }
}
