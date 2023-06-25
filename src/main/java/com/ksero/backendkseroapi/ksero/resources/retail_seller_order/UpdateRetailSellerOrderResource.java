package com.ksero.backendkseroapi.ksero.resources.retail_seller_order;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRetailSellerOrderResource {

    private Long id;


    private Long quantity;

    private Long retailSellerId;
    private Long productId;
    private String operationCode = null;
    private boolean isPaid = false;

}
