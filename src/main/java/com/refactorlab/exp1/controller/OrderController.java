package com.refactorlab.exp1.controller;

import com.refactorlab.exp1.service.OrderService;

public  class OrderController {
    private final OrderService service = new OrderService();

    public String place(String userId, String sku, int qty, String coupon, String region) {
        try {
            return service.placeOrder(userId, sku, qty, coupon, region);
        } catch (Exception e) {
            // intentionally poor error handling (for refactor)
            return "FAILED: " + e.getMessage();
        }
    }

    public String dump() {
        return service.dumpData();
    }
}