package com.webshop.Controller;

import com.webshop.Service.CartServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.desktop.ScreenSleepEvent;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartServices cartServices;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam int productId) {
        cartServices.addToCart(productId);
        return ResponseEntity.ok("Product added to cart successfully!");
    }
    @PostMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam int productId) {
        cartServices.removeFromCart(productId);
        return ResponseEntity.ok("Product removed!");
    }
}
