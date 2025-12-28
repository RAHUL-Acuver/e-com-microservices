package com.ecommerce.demo.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
