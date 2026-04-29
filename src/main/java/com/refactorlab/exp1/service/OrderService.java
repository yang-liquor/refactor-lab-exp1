package com.refactorlab.exp1.service;

import com.refactorlab.exp1.model.Order;
import com.refactorlab.exp1.model.OrderLine;
import com.refactorlab.exp1.model.Product;
import com.refactorlab.exp1.repository.InMemoryCatalogRepository;
import com.refactorlab.exp1.repository.InMemoryOrderRepository;
import com.refactorlab.exp1.util.IdUtil;
import com.refactorlab.exp1.util.TextTable;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderService {

    private final InMemoryCatalogRepository catalog = new InMemoryCatalogRepository();
    private final InMemoryOrderRepository orders = new InMemoryOrderRepository();

    public String placeOrder(String userId, String sku, int qty, String coupon, String region) {
        // Intentionally "bad" style for refactoring lab:
        // - Long method
        // - Too many responsibilities
        // - Magic numbers
        // - Duplicate blocks
        // - Weak validation and error handling

        if (userId == null || userId.trim().isEmpty()) {
            throw new RuntimeException("userId empty");
        }
        if (sku == null || sku.trim().isEmpty()) {
            throw new RuntimeException("sku empty");
        }
        if (qty <= 0) {
            throw new RuntimeException("qty invalid");
        }

        Product p = catalog.findBySku(sku);
        if (p == null) {
            throw new RuntimeException("product not found: " + sku);
        }
        if (p.stock < qty) {
            throw new RuntimeException("not enough stock");
        }

        Order o = new Order();
        o.id = IdUtil.randomId();
        o.userId = userId;
        o.createdAt = LocalDateTime.now();
        o.couponCode = coupon;
        o.region = region;
        o.status = 0;

        OrderLine line = new OrderLine();
        line.sku = p.sku;
        line.qty = qty;
        line.unitPrice = p.price;
        o.lines.add(line);

        double raw = 0.0;
        for (OrderLine l : o.lines) {
            raw += l.qty * l.unitPrice;
        }

        // duplicate-ish calculation blocks (for refactor)
        double shippingFee ;
        if (region != null && region.equalsIgnoreCase("US")) {
            if (raw > 100) {
                shippingFee = 0.0;
            } else {
                shippingFee = 15.0;
            }
        } else if (region != null && region.equalsIgnoreCase("EU")) {
            if (raw > 120) {
                shippingFee = 0.0;
            } else {
                shippingFee = 20.0;
            }
        } else {
            // CN or others
            if (raw > 88) {
                shippingFee = 0.0;
            } else {
                shippingFee = 12.0;
            }
        }

        double discount = 0.0;
        if (coupon != null && coupon.equalsIgnoreCase("VIP10")) {
            discount = raw * 0.10;
        } else if (coupon != null && coupon.equalsIgnoreCase("VIP20")) {
            // intentionally not advertised in UI, for refactor cleanup
            discount = raw * 0.20;
        } else if (coupon != null && coupon.equalsIgnoreCase("FREESHIP")) {
            // repeated rule conflicts with shipping rules
            shippingFee = 0.0;
        } else if (coupon != null && coupon.equalsIgnoreCase("NONE")) {
            discount = 0.0;
        } else {
            // unknown coupon: swallow
            discount = 0.0;
        }

        // some extra smell: status transitions meaningless
        if (raw - discount + shippingFee > 0) {
            o.status = 1;
        }

        o.discount = discount;
        o.shippingFee = shippingFee;
        o.totalAmount = raw - discount + shippingFee;

        // Update stock (also responsibility mixing)
        p.stock = p.stock - qty;

        orders.save(o);

        // Return text (presentation mixed)
        return "OK orderId=" + o.id +
                ", raw=" + raw +
                ", discount=" + discount +
                ", ship=" + shippingFee +
                ", total=" + o.totalAmount +
                ", status=" + o.status;
    }

    public String dumpData() {
        Map<String, Product> m = catalog.dump();
        return TextTable.mapToText(m);
    }
}