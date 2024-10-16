package com.application.bsbookservice.service;

import com.application.bsbookservice.dto.category.CategoryByIdsRequestDto;
import com.application.bsbookservice.dto.category.CategoryDto;
import com.application.bsbookservice.feign.client.CategoryFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryFeignClient categoryFeignClient;
    private final Logger logger;

    @CircuitBreaker(name = "categoryService", fallbackMethod = "fallbackGetCategoryDetailsByIds")
    public List<CategoryDto> getCategoryDetailsByIds(CategoryByIdsRequestDto requestDto) {
        return categoryFeignClient.getCategoryDetailsByIds(requestDto);
    }

    public List<CategoryDto> fallbackGetCategoryDetailsByIds(
            CategoryByIdsRequestDto requestDto,
            Throwable tw
    ) {
        logger.warning(tw.getMessage());
        return Collections.emptyList();
    }
}
