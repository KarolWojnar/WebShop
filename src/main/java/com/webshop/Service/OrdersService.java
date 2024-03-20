package com.webshop.Service;

import com.webshop.Model.Orders;
import com.webshop.Repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
}
