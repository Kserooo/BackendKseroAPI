package com.ksero.backendkseroapi.ksero.api.resources.retail_seller_order;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRetailSellerOrderResource {

    private Long id;

    @NotNull
    @NotBlank
    @Positive
    private Long quantity;

}
