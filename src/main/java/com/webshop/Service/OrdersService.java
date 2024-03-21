package com.webshop.Service;

import com.webshop.Model.OrderItem;
import com.webshop.Model.Orders;
import com.webshop.Repository.OrderItemRepository;
import com.webshop.Repository.OrdersRepository;
import com.webshop.interfaces.CrudInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService implements CrudInterface<Orders> {
    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public List<Orders> getAll() {
        return ordersRepository.findAll();
    }

    @Override
    @Transactional
    public Orders add(Orders order) {
        return ordersRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(int id) {
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
