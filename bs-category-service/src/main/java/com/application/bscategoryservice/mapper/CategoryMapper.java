package com.application.bscategoryservice.mapper;

import com.application.bscategoryservice.config.MapperConfig;
import com.application.bscategoryservice.dto.category.CategoryDto;
import com.application.bscategoryservice.dto.category.CreateCategoryRequestDto;
import com.application.bscategoryservice.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toModel(CreateCategoryRequestDto requestDto);
}
