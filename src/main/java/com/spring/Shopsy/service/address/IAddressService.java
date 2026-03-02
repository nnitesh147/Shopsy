package com.spring.Shopsy.service.address;

import com.spring.Shopsy.model.User;
import com.spring.Shopsy.payload.address.AddressDTO;

import java.util.List;

public interface IAddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAddresses();

    AddressDTO getAddressesById(Long addressId);

    List<AddressDTO> getUserAddresses(User user);

    AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);

    String deleteAddress(Long addressId);
}