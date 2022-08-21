package com.ksero.backendkseroapi.ksero.resources.product;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
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
    private Wholesaler wholesaler;

    @NotNull
    private Double price;
}
