package com.application.query.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductResponseDto {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
