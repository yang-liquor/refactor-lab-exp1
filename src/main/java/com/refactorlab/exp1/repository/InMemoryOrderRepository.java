package com.refactorlab.exp1.repository;

import com.refactorlab.exp1.model.Order;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public void save(Order o) {
        orders.add(o);
    }

    @SuppressWarnings("unused") // 新增这行
    public List<Order> list() {
        return orders;
    }
}