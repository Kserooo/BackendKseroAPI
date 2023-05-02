package com.ksero.backendkseroapi.ksero.resources.wholesaler_order;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWholesalerOrderResource {

    private Long id;

    private Long quantity;

    private Long retailSellerId;
    private Long productId;

}
