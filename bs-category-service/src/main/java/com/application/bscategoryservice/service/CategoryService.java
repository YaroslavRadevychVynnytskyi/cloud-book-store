package com.application.bscategoryservice.service;

import com.application.bscategoryservice.dto.category.CategoryByIdsRequestDto;
import com.application.bscategoryservice.dto.category.CategoryDto;
import com.application.bscategoryservice.dto.category.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto createCategory(CreateCategoryRequestDto categoryDto);

    CategoryDto getById(Long categoryId);

    CategoryDto update(Long categoryId, CreateCategoryRequestDto requestDto);

    void deleteById(Long categoryId);

    List<CategoryDto> getCategoryDetailsByIds(CategoryByIdsRequestDto requestDto);
}
