package com.refactorlab.exp1.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void placeOrder_shouldWork_forVip10() {
        OrderService s = new OrderService();
        String result = s.placeOrder("u1", "A", 2, "VIP10", "CN");
        assertTrue(result.startsWith("OK"));
        assertTrue(result.contains("discount="));
    }

    @Test
    void placeOrder_shouldThrow_whenQtyInvalid() {
        OrderService s = new OrderService();
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> s.placeOrder("u1", "A", 0, "NONE", "CN"));
        assertTrue(ex.getMessage().contains("qty"));
    }

    @Test
    void placeOrder_shouldApplyFreeShip() {
        OrderService s = new OrderService();
        String result = s.placeOrder("u1", "B", 1, "FREESHIP", "US");
        assertTrue(result.contains("ship=0.0"));
    }
}