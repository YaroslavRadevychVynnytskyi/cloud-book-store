package com.application.bsbookservice.feign.client;

import com.application.bsbookservice.dto.category.CategoryByIdsRequestDto;
import com.application.bsbookservice.dto.category.CategoryDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bs-api-gateway", path = "bs-category-service/categories")
public interface CategoryFeignClient {
    @PostMapping("/details-by-ids")
    List<CategoryDto> getCategoryDetailsByIds(@RequestBody CategoryByIdsRequestDto requestDto);
}
