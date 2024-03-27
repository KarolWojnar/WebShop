package com.webshop.Repository;

import com.webshop.Model.Orders;
import com.webshop.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o")
    List<Orders> findAll();
    Optional<Orders> getOrdersByUserAndStatus(User user, String status);
}
