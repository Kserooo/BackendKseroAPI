package com.ksero.backendkseroapi.ksero.resources.product;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResource {
    @NotBlank
    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 240)
    private String description;
    private Long wholesalerId;

    @NotNull
    private Number price;
}
