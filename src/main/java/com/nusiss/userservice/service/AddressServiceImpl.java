package com.nusiss.userservice.service;

import com.nusiss.userservice.entity.Address;
import com.nusiss.userservice.dao.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> getAddressById(Integer addressId) {
        return addressRepository.findById(addressId);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public List<Address> getAddressesByUserId(Integer userId) {
        return addressRepository.findByUserUserId(userId); // 新增方法实现
    }

    @Override
    public Address updateAddress(Integer addressId, Address address) {
        if (addressRepository.existsById(addressId)) {
            address.setAddressId(addressId);
            return addressRepository.save(address);
        } else {
            throw new RuntimeException("Address not found with id " + addressId);
        }
    }

    @Override
    public void deleteAddress(Integer addressId) {
        addressRepository.deleteById(addressId);
    }
}
