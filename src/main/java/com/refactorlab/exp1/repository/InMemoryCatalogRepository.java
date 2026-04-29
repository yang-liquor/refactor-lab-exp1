package com.refactorlab.exp1.repository;

import com.refactorlab.exp1.model.Product;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCatalogRepository {
    private final Map<String, Product> data = new HashMap<>();

    public InMemoryCatalogRepository() {
        Product a = new Product();
        a.sku = "A";
        a.name = "Apple";
        a.price = 19.9;
        a.stock = 50;

        Product b = new Product();
        b.sku = "B";
        b.name = "Banana";
        b.price = 9.9;
        b.stock = 20;

        Product c = new Product();
        c.sku = "C";
        c.name = "Cookie";
        c.price = 29.9;
        c.stock = 5;

        data.put(a.sku, a);
        data.put(b.sku, b);
        data.put(c.sku, c);
    }

    public Product findBySku(String sku) {
        return data.get(sku);
    }

    public Map<String, Product> dump() {
        return data;
    }
}