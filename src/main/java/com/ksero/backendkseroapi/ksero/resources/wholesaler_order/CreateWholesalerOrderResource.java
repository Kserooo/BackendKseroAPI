package com.ksero.backendkseroapi.ksero.resources.wholesaler_order;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateWholesalerOrderResource {

    @NotNull
    @Positive
    private Long quantity;

    private Long retailSellerId;
    private Long productId;
    private String operationCode;

    private boolean isPaid;

    public CreateWholesalerOrderResource(
        Long id,
        Long quantity,
        Long retailSellerId,
        Long productId
    ) {
        this.quantity = quantity;
        this.retailSellerId = retailSellerId;
        this.productId = productId;
        this.operationCode = null;
        this.isPaid = false;
    }
}
