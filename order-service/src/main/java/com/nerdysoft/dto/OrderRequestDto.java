package com.nerdysoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequestDto {
    private String productId;
    private Integer quantity;
    private String addressId;
}
