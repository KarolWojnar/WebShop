package com.webshop.Repository;

import com.webshop.Model.OrderItem;
import com.webshop.Model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    Iterable<OrderItem> findAllByOrder(Orders order);
    void deleteAllByOrder(Orders order);
}
