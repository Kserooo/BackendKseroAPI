package com.ksero.backendkseroapi.ksero.api.resources.wholesaler_order;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWholesalerOrderResource {

    private Long id;

    @NotNull
    @NotBlank
    @Positive
    private Long quantity;

    private Long retailSellerId;
    private Long productId;

}
