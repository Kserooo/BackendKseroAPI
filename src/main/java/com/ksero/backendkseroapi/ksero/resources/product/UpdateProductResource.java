package com.ksero.backendkseroapi.ksero.resources.product;

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
    private Long wholesalerId;
    private Number price;
}
