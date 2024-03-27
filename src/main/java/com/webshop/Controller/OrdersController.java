package com.webshop.Controller;

import com.webshop.Model.Code;
import com.webshop.Model.Orders;
import com.webshop.Service.OrdersService;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.getAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Orders> addNewOrder(@RequestBody Orders order) {
        Orders newOrder = ordersService.add(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Orders> deleteOrder(@PathVariable int id) {
        ordersService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/promo")
    public ResponseEntity<String> checkPromoCode(@RequestBody String promocode) {
        ordersService.findCode(promocode);
        return new ResponseEntity<>(promocode, HttpStatus.OK);
    }
}
