package com.ksero.backendkseroapi.security.domain.service.communication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

    private String creditCardNumber;

    private Set<String> roles;
}
