package com.ksero.backendkseroapi.ksero.resources.product;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private String image;

    private Long wholesalerId;

    //private Wholesaler wholesaler;

    @NotNull
    private Double price;
}
