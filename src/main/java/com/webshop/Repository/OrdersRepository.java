package com.webshop.Repository;

import com.webshop.Model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o")
    List<Orders> findAll();
}
