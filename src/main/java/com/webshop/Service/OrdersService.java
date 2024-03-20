package com.webshop.Service;

import com.webshop.Model.OrderItem;
import com.webshop.Model.Orders;
import com.webshop.Repository.OrderItemRepository;
import com.webshop.Repository.OrdersRepository;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Transactional
    public Orders addOrder(Orders order) {
        return ordersRepository.save(order);
    }

    @Transactional
    public void deleteOrder(int id) {
        Optional<Orders> optionalOrder = ordersRepository.findById((long) id);
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            Iterable<OrderItem> orderItemIterator = orderItemRepository.findAllByOrder(order);

            for (OrderItem i : orderItemIterator) {
                orderItemRepository.delete(i);
            }
            ordersRepository.delete(order);
        }
    }
}
