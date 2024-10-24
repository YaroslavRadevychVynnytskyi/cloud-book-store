package com.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateProductRequestDto {
    @NotBlank(message = "Product title is a required field")
    private String title;

    @Min(value = 1, message = "Product price can not be lower than 1")
    private BigDecimal price;

    @Min(value = 1, message = "Product quantity can not be lower than 1")
    @Max(value = 99, message = "Product quantity can not be higher than 99")
    private Integer quantity;
}
