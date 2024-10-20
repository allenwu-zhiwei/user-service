package com.nusiss.userservice.service;

import com.nusiss.userservice.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address createAddress(Address address);

    Optional<Address> getAddressById(Integer addressId);

    List<Address> getAllAddresses();

    List<Address> getAddressesByUserId(Integer userId);

    Address updateAddress(Integer addressId, Address address);

    void deleteAddress(Integer addressId);
}
