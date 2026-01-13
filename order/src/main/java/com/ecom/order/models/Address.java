package com.ecom.order.models;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Address {

    @Id
    private String id;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String street;
//    private AddressDTO address;
}
