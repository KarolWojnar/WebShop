package com.webshop.Repository;

import com.webshop.Model.Cart;
import com.webshop.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByUser(User user);
}
