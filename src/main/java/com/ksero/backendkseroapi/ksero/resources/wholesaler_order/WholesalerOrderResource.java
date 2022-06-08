package com.ksero.backendkseroapi.ksero.resources.wholesaler_order;

import lombok.*;

@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class WholesalerOrderResource {

    private Long id;
    private Long quantity;
    private Long retailSellerId;
    private Long productId;

}
