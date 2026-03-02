package com.spring.Shopsy.payload.order;

import com.spring.Shopsy.model.Address;
import lombok.Data;

import java.util.Map;

@Data
public class StripePaymentDto {
    private Long amount;
    private String currency;
    private String email;
    private String name;
    private Address address;
    private String description;
    private Map<String, String> metadata;
}