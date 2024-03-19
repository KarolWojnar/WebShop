package com.webshop;

import com.webshop.Model.Cart;
import com.webshop.Model.CartItem;
import com.webshop.Model.Product;
import com.webshop.Model.User;
import com.webshop.Repository.CartItemRepository;
import com.webshop.Repository.CartRepository;
import com.webshop.Repository.ProductRepository;
import com.webshop.Service.CartServices;
import com.webshop.Service.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartServiceTest {
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private CartServices cartServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testAddToCartProductNotFound() {
        int productId = 1;
        when(productRepository.findById((long) productId)).thenReturn(Optional.empty());

        cartServices.addToCart(productId);
        verify(cartItemRepository, never()).save(any(CartItem.class));
    }
    @Test
    void testAddToCartNewCartItem() {
        int productId = 1;
        User user = new User();
        Product product = new Product();
        Cart cart = new Cart();

        when(productRepository.findById((long) productId)).thenReturn(Optional.of(product));
        when(userService.getAuthUser()).thenReturn(user);
        when(cartRepository.findByUser(user)).thenReturn(cart);
        when(cartItemRepository.findByCartAndProduct(cart, product)).thenReturn(Optional.empty());

        cartServices.addToCart(productId);
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }
    @Test
    void testAddToCart() {
        int productId = 1;
        User user = new User();
        Product product = new Product();
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(2);

        when(productRepository.findById((long) productId)).thenReturn(Optional.of(product));
        when(userService.getAuthUser()).thenReturn(user);
        when(cartRepository.findByUser(user)).thenReturn(cart);
        when(cartItemRepository.findByCartAndProduct(cart, product)).thenReturn(Optional.of(cartItem));

        cartServices.addToCart(productId);

        assertEquals(3, cartItem.getQuantity());
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }
    @Test
    void removeFromCartWhenSingleItem() {
        int productId = 1;
        User user = new User();
        Cart cart = new Cart();
        Product product = new Product();
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);

        when(productRepository.findById((long) productId)).thenReturn(Optional.of(product));
        when(userService.getAuthUser()).thenReturn(user);
        when(cartRepository.findByUser(user)).thenReturn(cart);
        when(cartItemRepository.findByCartAndProduct(cart, product)).thenReturn(Optional.of(cartItem));
        // when
        cartServices.removeFromCart(productId);
        // then
        verify(cartItemRepository, times(1)).findByCartAndProduct(cart, product);
        verify(cartItemRepository, times(1)).delete(any(CartItem.class));
        verify(cartItemRepository, never()).save(any(CartItem.class));
    }

    @Test
    void removeFromCartWhenMultipleItems() {
        int productId = 1;
        User user = new User();
        Cart cart = new Cart();
        Product product = new Product();
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(2);

        when(productRepository.findById((long) productId)).thenReturn(Optional.of(product));
        when(userService.getAuthUser()).thenReturn(user);
        when(cartRepository.findByUser(user)).thenReturn(cart);
        when(cartItemRepository.findByCartAndProduct(cart, product)).thenReturn(Optional.of(cartItem));
        // when
        cartServices.removeFromCart(productId);
        // then
        verify(cartItemRepository, times(1)).findByCartAndProduct(cart, product);
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
        verify(cartItemRepository, never()).delete(any(CartItem.class));
    }
}
