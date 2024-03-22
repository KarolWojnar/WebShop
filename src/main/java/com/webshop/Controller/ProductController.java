package com.webshop.Controller;

import com.webshop.Model.Product;
import com.webshop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newItem = productService.add(product);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newItem);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product updatedProduct) {
        updatedProduct.setProductId(productId);
        Product updatedProductResult = productService.updateProduct(updatedProduct);
        if (updatedProductResult != null) {
            return ResponseEntity.ok(updatedProductResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
