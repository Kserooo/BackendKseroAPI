package com.ksero.backendkseroapi.ksero.resources.retail_seller;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRetailSellerResource {

    private Long id;

    private String firstName;

    private String address;

    private String lastName;

    private Date birthday;

    private int phone;

    private String email;

    private String username;

    private String password;

    private String description;
}
