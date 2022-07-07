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
    private String name;
    
    @NotNull
    @NotBlank
    private String description;
    private Long wholesalerId;

    @NotNull
    private Double price;
}
