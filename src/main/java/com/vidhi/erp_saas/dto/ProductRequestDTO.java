package com.vidhi.erp_saas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotBlank
    private String name;

    @Positive
    private Double price;

    @PositiveOrZero
    private Long quantity;
}
