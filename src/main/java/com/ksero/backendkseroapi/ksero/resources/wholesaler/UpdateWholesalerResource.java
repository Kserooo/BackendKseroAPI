package com.ksero.backendkseroapi.ksero.resources.wholesaler;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWholesalerResource {
    private Long id;

    private String firstName;

    private String address;

    private String lastName;

    private String birthday;

    private String phone;

    private String email;

    private String username;

    private String password;

    private String description;
}
