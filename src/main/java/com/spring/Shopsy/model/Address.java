package com.spring.Shopsy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank(message = "street cannot be blank")
    @Size(min = 5, message = "street name must be atleast 5 char")
    private String street;

    @NotBlank
    @Size(min = 5, message = "building name must be atleast 5 char")
    private String buildingName;

    @NotBlank
    @Size(min = 3, message = "City name must be atleast 3 char")
    private String city;

    @NotBlank
    @Size(min = 3, message = "State name must be atleast 3 char")
    private String state;

    @NotBlank
    @Size(min = 3, message = "Country name must be atleast 3 char")
    private String country;

    public Address(String street, String buildingName, String city, String state, String country, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    @NotBlank
    @Size(min = 6, message = "PinCode name must be atleast 6 char")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

}
