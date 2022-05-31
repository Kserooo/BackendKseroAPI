package com.ksero.backendkseroapi.ksero.api.resources.retail_seller;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class RetailSellerResource {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private int phone;
    private String email;
    private String address;
    private String username;
    private String password;
    private String description;
}
