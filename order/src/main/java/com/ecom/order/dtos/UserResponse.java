package com.ecom.order.dtos;


import com.ecom.order.models.Address;
import lombok.Data;

@Data
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
    private Address address;

}
