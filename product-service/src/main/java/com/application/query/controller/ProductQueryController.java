package com.application.query.controller;

import com.application.query.FindProductsQuery;
import com.application.query.dto.ProductResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductQueryController {
    private final QueryGateway queryGateway;

    @GetMapping
    public List<ProductResponseDto> getProducts() {
        FindProductsQuery findProductsQuery = new FindProductsQuery();

        return queryGateway.query(findProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductResponseDto.class)).join();
    }
}
