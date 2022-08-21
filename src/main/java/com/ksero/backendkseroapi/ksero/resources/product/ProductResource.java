package com.ksero.backendkseroapi.ksero.resources.product;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import lombok.*;

@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProductResource {
    private Long id;
    private String name;
    private String description;
    private Wholesaler wholesalerId;
    private Double price;
}
