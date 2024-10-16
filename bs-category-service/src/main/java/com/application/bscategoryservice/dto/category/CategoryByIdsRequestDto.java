package com.application.bscategoryservice.dto.category;

import java.util.List;

public record CategoryByIdsRequestDto(
        List<Long> categoryIds
) {
}
