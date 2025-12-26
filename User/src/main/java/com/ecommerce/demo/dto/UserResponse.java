package com.ecommerce.demo.dto;


import com.ecommerce.demo.models.Address;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
    private Address address;

}
