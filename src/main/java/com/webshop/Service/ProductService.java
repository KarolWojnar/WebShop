package com.webshop.Service;

import com.webshop.Model.Cart;
import com.webshop.Model.CartItem;
import com.webshop.Model.Product;
import com.webshop.Model.User;
import com.webshop.Repository.CartItemRepository;
import com.webshop.Repository.CartRepository;
import com.webshop.Repository.ProductRepository;
import com.webshop.Service.User.UserService;
import com.webshop.interfaces.CrudInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements CrudInterface<Product> {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.getAllProducts();
    }

    public void returnProduct(int id, Model model) {
        Optional<Product> optional = productRepository.findById((long) id);
        if (optional.isPresent()) {
            Product product = optional.get();
            model.addAttribute("product", product);
        } else model.addAttribute("notFound", "Product Not Found");
    }

    @Transactional
    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public void delete(int productId) {
        productRepository.deleteProductByProductId(productId);
    }

    @Transactional
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

    public List<Product> getCart(Model model) {
        User user = userService.getAuthUser(model);
        return getItemsByUser(user, model);
    }
    private List<Product> getItemsByUser(User user, Model model) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) return Collections.emptyList();

        List<CartItem> cartItems = cartItemRepository.getCartItemsByCart(cart);
        List<Product> products = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        double totalPrice = 0;
        for (CartItem item: cartItems) {
            products.add(item.getProduct());
            quantity.add(item.getQuantity());
            totalPrice += (item.getProduct().getPrice() * item.getQuantity());
        }
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalPrice", totalPrice);
        return products;
    }
}
