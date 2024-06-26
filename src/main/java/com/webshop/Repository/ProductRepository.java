package com.webshop.Repository;

import com.webshop.Model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findProductByTitle(String name);
    @Query("SELECT p FROM Product p JOIN FETCH p.categoryId c")
    List<Product> getAllProducts();

    void deleteProductByProductId(int id);
}
