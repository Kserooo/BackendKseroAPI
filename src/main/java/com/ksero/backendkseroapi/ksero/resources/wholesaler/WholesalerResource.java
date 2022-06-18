package com.ksero.backendkseroapi.ksero.resources.wholesaler;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class WholesalerResource {
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
