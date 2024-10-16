package com.application.bsbookservice.dto.book;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record BookSearchParameters(
        String[] authors,

        @Min(0) @Max(1000_0000)
        Double minPrice,

        @Min(0) @Max(1000_0000)
        Double maxPrice
) {
}
