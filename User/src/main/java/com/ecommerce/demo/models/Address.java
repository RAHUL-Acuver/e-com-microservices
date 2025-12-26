package com.ecommerce.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String street;
//    private AddressDTO address;
}
