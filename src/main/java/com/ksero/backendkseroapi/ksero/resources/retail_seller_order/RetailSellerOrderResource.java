package com.ksero.backendkseroapi.ksero.resources.retail_seller_order;

import lombok.*;

@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class RetailSellerOrderResource {

    private Long id;
    private Long quantity;
    private Long retailSellerId;
    private Long productId;
    private String operationCode = null;
    private boolean isPaid = false;

}
