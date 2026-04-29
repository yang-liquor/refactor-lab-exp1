package com.refactorlab.exp1.service;

import com.refactorlab.exp1.controller.OrderService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void placeOrder_shouldWork_forVip10() {
        com.refactorlab.exp1.controller.OrderService s = new com.refactorlab.exp1.controller.OrderService();
        String result = s.placeOrder("u1", "A", 2, "VIP10", "CN");
        assertTrue(result.startsWith("OK"));
        assertTrue(result.contains("discount="));
    }

    @Test
    void placeOrder_shouldThrow_whenQtyInvalid() {
        com.refactorlab.exp1.controller.OrderService s = new com.refactorlab.exp1.controller.OrderService();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> s.placeOrder("u1", "A", 0, "NONE", "CN"));
        assertTrue(ex.getMessage().contains("qty"));
    }

    @Test
    void placeOrder_shouldApplyFreeShip() {
        com.refactorlab.exp1.controller.OrderService s = new OrderService();
        String result = s.placeOrder("u1", "B", 1, "FREESHIP", "US");
        assertTrue(result.contains("ship=0.0"));
    }
}