package com.webshop.Service;

import com.webshop.Model.Product;
import com.webshop.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public void getAllProducts(Model model) {
        Iterable<Product> products = productRepository.getAllProducts();
        model.addAttribute("products", products);
    }

    public void returnProduct(int id, Model model) {
        Optional<Product> optional = productRepository.findById((long) id);
        if (optional.isPresent()) {
            Product product = optional.get();
            model.addAttribute("product", product);
        } else model.addAttribute("notFound", "Product Not Found");
    }
}
