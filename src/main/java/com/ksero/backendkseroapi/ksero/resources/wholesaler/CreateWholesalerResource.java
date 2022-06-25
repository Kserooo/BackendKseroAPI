package com.ksero.backendkseroapi.ksero.resources.wholesaler;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateWholesalerResource {
    @NotNull
    private String firstName;
    @NotNull
    private String address;
    @NotNull
    private String lastName;
    @NotNull
    private String birthday;
    @NotNull
    private String phone;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    private String description;
}
