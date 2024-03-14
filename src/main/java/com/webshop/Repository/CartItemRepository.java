package com.webshop.Repository;

import com.webshop.Model.Cart;
import com.webshop.Model.CartItem;
import com.webshop.Model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    List<CartItem> getCartItemsByCart(Cart cart);
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
