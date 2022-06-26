package com.ksero.backendkseroapi.ksero.resources.retail_seller;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateRetailSellerResource {

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private String birthday;

    @NotNull
    @NotBlank
    private String phone;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String description;
}
