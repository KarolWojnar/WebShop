package com.webshop.Controller;

import com.webshop.Service.CartServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartServices cartServices;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam int productId) {
        System.out.println(productId);
        cartServices.addToCart(productId);
        return ResponseEntity.ok("Product added to cart successfully!");
    }
}
