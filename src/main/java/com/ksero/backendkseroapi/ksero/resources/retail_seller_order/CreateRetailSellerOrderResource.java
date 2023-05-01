package com.ksero.backendkseroapi.ksero.resources.retail_seller_order;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateRetailSellerOrderResource {

    @NotNull
    @Positive
    private Long quantity;

    private Long retailSellerId;
    private Long productId;

}
