package ru.demo.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demo.shop.models.Address;
import ru.demo.shop.repositories.AddressesRepository;

@Service
public class AddressesService {

    private final AddressesRepository addressesRepository;

    @Autowired
    public AddressesService(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    public Address findOne(int addressId){
        return addressesRepository.findById(addressId).orElse(null);
    }

    @Transactional
    public void save(Address address){
        addressesRepository.save(address);
    }
}
