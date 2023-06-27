package com.ksero.backendkseroapi.ksero.resources.product;

import javax.validation.constraints.NotNull;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductResource {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String image;
    @NotNull
    private Long wholesalerId;
    @NotNull
    private Double price;
}
