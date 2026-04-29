package com.refactorlab.exp1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    public String id;
    public String userId;
    public LocalDateTime createdAt;
    public final List<OrderLine> lines = new ArrayList<>();
    public String couponCode;
    public String region;

    public int status; // 0 created, 1 paid, 2 shipped, 9 cancelled

    public double totalAmount;
    public double shippingFee;
    public double discount;
}