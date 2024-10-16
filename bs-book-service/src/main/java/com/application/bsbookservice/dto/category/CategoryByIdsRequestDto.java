package com.application.bsbookservice.dto.category;

import java.util.List;

public record CategoryByIdsRequestDto(
        List<Long> categoryIds
) {
}
