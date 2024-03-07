package com.webshop.Repository;

import com.webshop.Model.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OderItemRepository extends CrudRepository<OrderItem, Long> {
}
