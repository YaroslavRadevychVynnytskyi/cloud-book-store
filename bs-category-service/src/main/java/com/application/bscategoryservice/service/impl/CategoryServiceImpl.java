package com.application.bscategoryservice.service.impl;

import com.application.bscategoryservice.dto.category.CategoryByIdsRequestDto;
import com.application.bscategoryservice.dto.category.CategoryDto;
import com.application.bscategoryservice.dto.category.CreateCategoryRequestDto;
import com.application.bscategoryservice.mapper.CategoryMapper;
import com.application.bscategoryservice.model.Category;
import com.application.bscategoryservice.repo.CategoryRepository;
import com.application.bscategoryservice.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto createCategory(CreateCategoryRequestDto categoryDto) {
        Category category = categoryMapper.toModel(categoryDto);

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDto getById(Long categoryId) {
        Category category = getCategoryById(categoryId);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto update(Long categoryId, CreateCategoryRequestDto requestDto) {
        Category category = getCategoryById(categoryId);
        category.setName(requestDto.name());
        category.setDescription(requestDto.description());

        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryDto> getCategoryDetailsByIds(CategoryByIdsRequestDto requestDto) {
        List<Category> categories = categoryRepository.findAllById(requestDto.categoryIds());
        return categories.stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("Category with ID: " + categoryId + " not found"));
    }
}
