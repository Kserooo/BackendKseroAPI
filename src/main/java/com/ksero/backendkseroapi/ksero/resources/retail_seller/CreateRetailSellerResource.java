package com.ksero.backendkseroapi.ksero.resources.retail_seller;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateRetailSellerResource {

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
