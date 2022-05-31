package com.ksero.backendkseroapi.ksero.api.resources.product;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateProductResource {
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 240)
    private String description;

    private Long wholesalerId;

    @NotNull
    @NotBlank
    private Number price;
}
