package com.webshop.Controller;

import com.webshop.Model.Address;
import com.webshop.Model.Cart;
import com.webshop.Model.CartItem;
import com.webshop.Model.Product;
import com.webshop.Repository.CartItemRepository;
import com.webshop.Repository.CartRepository;
import com.webshop.Service.CartServices;
import com.webshop.Service.OrdersService;
import com.webshop.Service.ProductService;
import com.webshop.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    private final UserService userService;
    private final OrdersService ordersService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    @GetMapping("/home")
    public String returnHome(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/product/{id}")
    public String returnProductPage(@PathVariable int id, Model model) {
        productService.returnProduct(id, model);
        return "product";
    }
    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("products", productService.getCart(model));
        return "cart";
    }
    @GetMapping("/order")
    public String getOrder(Model model) {
        Cart cart = cartRepository.findByUser(userService.getAuthUser());
        List<CartItem> cartItem = cartItemRepository.getCartItemsByCart(cart);
        List<Product> products = productService.getCart(model);
        Address address = userService.getAddress();
        ordersService.createOrder(cartItem, address);
        model.addAttribute("products", products);
        model.addAttribute("address", address);
        return "order";
    }
}
