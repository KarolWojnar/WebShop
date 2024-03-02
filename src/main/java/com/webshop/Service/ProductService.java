package com.webshop.Service;

import com.webshop.Model.Product;
import com.webshop.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public void getAllProducts(Model model) {
        Iterable<Product> products = productRepository.getAllProducts();
        model.addAttribute("products", products);
    }
}
