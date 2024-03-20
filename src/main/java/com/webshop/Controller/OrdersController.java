package com.webshop.Controller;

import com.webshop.Model.Orders;
import com.webshop.Service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Orders> addNewOrder(@RequestBody Orders order) {
        Orders newOrder = ordersService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Orders> deleteOrder(@PathVariable int id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
