package com.webshop.Service;

import com.webshop.Model.Product;
import com.webshop.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.getAllProducts();
        return products;
    }

    public void returnProduct(int id, Model model) {
        Optional<Product> optional = productRepository.findById((long) id);
        if (optional.isPresent()) {
            Product product = optional.get();
            model.addAttribute("product", product);
        } else model.addAttribute("notFound", "Product Not Found");
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(int productId) {
        productRepository.deleteProductByProductId(productId);
    }

    public Product updateProduct(Product updatedProduct) {
        Optional<Product> optional = productRepository.findById(updatedProduct.getProductId());
        if (optional.isPresent()) {
            Product existingProduct = optional.get();

            existingProduct.setTitle(updatedProduct.getTitle());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
            existingProduct.setCategoryId(updatedProduct.getCategoryId());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setCountRate(existingProduct.getCountRate());
            existingProduct.setRate(updatedProduct.getRate());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }
}
