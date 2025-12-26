package com.ecommerce.demo.dto;

import com.ecommerce.demo.models.Address;
import lombok.Data;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;

}
