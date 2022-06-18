package com.ksero.backendkseroapi.ksero.resources.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
