package com.webshop.Service;

import com.webshop.Model.Cart;
import com.webshop.Model.CartItem;
import com.webshop.Model.Product;
import com.webshop.Model.User;
import com.webshop.Repository.CartItemRepository;
import com.webshop.Repository.CartRepository;
import com.webshop.Repository.ProductRepository;
import com.webshop.Service.User.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServices {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    @Transactional
    public void addToCart(int productId) {
        Optional<Product> optional = productRepository.findById((long) productId);
        if (optional.isEmpty()) return;
        Product product = optional.get();
        User user = userService.getAuthUser();
        if (user != null) {
            Cart cart = cartRepository.findByUser(user);
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                cart.setStatus("status");
                cartRepository.save(cart);
            }
            Optional<CartItem> optionalCartItem = cartItemRepository.findByCartAndProduct(cart, product);
            if (optionalCartItem.isPresent()) {
                CartItem cartItem = optionalCartItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemRepository.save(cartItem);
            } else {
                CartItem newCartItem = new CartItem();
                newCartItem.setQuantity(1);
                newCartItem.setCart(cart);
                newCartItem.setProduct(product);
                cartItemRepository.save(newCartItem);
            }
        }
    }
}
