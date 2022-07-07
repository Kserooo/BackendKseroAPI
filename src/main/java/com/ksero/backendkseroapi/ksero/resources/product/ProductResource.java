package com.ksero.backendkseroapi.ksero.resources.product;

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
    private Long wholesalerId;
    private Double price;
}
