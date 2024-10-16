package com.application.bsbookservice.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String isbn;

    @Min(1)
    private BigDecimal price;

    @Size(min = 10, max = 255)
    private String description;

    private String coverImage;

    private List<Long> categoryIds;
}
