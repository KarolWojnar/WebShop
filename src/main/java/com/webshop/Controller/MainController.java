package com.webshop.Controller;

import com.webshop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;

    @GetMapping("/home")
    public String returnHome(Model model) {
        productService.getAllProducts(model);
        return "home";
    }
}
