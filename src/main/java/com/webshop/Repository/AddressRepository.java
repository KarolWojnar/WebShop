package com.webshop.Repository;

import com.webshop.Model.Address;
import com.webshop.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    Address getFirstByUser(User user);
}
