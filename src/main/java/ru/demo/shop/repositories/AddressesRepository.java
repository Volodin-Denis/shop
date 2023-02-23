package ru.demo.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.shop.models.Address;

@Repository
public interface AddressesRepository extends JpaRepository<Address, Integer> {

}
