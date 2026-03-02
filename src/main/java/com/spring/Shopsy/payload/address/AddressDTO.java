package com.spring.Shopsy.payload.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long addressId;
    private String street;
    private String buildingName;

    @NotBlank(message = "City Cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Size(min = 2)
    private String state;

    @NotBlank(message = "Country cannot be blank")
    @Size(min = 3)
    private String country;

    @NotBlank(message = "pincode cannot be blank")
    @Size(min = 6, max = 6)
    private String pincode;
}