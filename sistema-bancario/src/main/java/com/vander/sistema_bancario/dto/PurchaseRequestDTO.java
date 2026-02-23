package com.vander.sistema_bancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseRequestDTO {

    private Long userId;
    private Long productId;
    private Integer quantity;
}