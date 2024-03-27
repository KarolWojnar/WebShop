package com.webshop.Service;

import com.webshop.Model.*;
import com.webshop.Repository.OrderItemRepository;
import com.webshop.Repository.OrdersRepository;
import com.webshop.Service.User.UserService;
import com.webshop.interfaces.CrudInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService implements CrudInterface<Orders> {
    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;
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

    public void findCode(String promocode) {
    }

    @Transactional
    public void createOrder(List<CartItem> cartItem, Address address) {
        Optional<Orders> isOrder = ordersRepository.getOrdersByUserAndStatus(userService.getAuthUser(), "created");
        if (isOrder.isPresent()) {
            orderItemRepository.deleteAllByOrder(isOrder.get());
            ordersRepository.delete(isOrder.get());
        }
        Orders order = new Orders();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        order.setOrderDate(currentTime);
        order.setUser(userService.getAuthUser());
        order.setStatus("created");
        double price = 0;
        for (CartItem item : cartItem) {
            price += (item.getProduct().getPrice() * item.getQuantity());
        }
        order.setTotalPrice(BigDecimal.valueOf(price));
        order.setAdress(address.getCountry() + ", " + address.getCity() + " " + address.getZipCode() + ", " + address.getStreet());
        add(order);
        addItemsToOrder(cartItem, order);
    }

    private void addItemsToOrder(List<CartItem> cartItem, Orders order) {
        for (CartItem item : cartItem) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(BigDecimal.valueOf(item.getProduct().getPrice()));
            orderItemRepository.save(orderItem);
        }
    }
}
