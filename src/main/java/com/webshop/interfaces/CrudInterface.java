package com.webshop.interfaces;

import com.webshop.Model.Orders;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CrudInterface<T> {
    List<T> getAll();
    T add(T item);
    void delete(int id);
}
