package com.application.bscategoryservice.dto.category;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateCategoryRequestDto(
        @NotBlank
        String name,

        @Length(min = 1, max = 200)
        String description
) {
}
