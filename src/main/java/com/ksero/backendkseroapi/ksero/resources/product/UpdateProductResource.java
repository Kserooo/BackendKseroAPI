package com.ksero.backendkseroapi.ksero.resources.product;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductResource {
    private Long id;
    private String name;
    private String description;
    private Wholesaler wholesaler;
    private Double price;
}
