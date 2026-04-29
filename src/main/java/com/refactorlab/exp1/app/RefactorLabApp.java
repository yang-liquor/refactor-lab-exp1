package com.refactorlab.exp1.app;

import com.refactorlab.exp1.controller.OrderController;

import java.util.Scanner;

public class RefactorLabApp {

    public static void main(String[] args) {
        OrderController c = new OrderController();
        Scanner sc = new Scanner(System.in);

        System.out.println("Refactor Lab Exp1 - Console");
        System.out.println("1) Place order");
        System.out.println("2) Print seed data");
        System.out.println("0) Exit");

        while (true) {
            System.out.print("Choose: ");
            String s = sc.nextLine();
            if ("0".equals(s)) {
                System.out.println("Bye.");
                return;
            } else if ("1".equals(s)) {
                System.out.print("userId: ");
                String u = sc.nextLine();
                System.out.print("sku (A/B/C): ");
                String sku = sc.nextLine();
                System.out.print("qty: ");
                int q = Integer.parseInt(sc.nextLine());
                System.out.print("coupon (NONE/VIP10/FREESHIP): ");
                String cp = sc.nextLine();
                System.out.print("region (CN/US/EU): ");
                String r = sc.nextLine();

                String result = c.place(u, sku, q, cp, r);
                System.out.println(result);
            } else if ("2".equals(s)) {
                System.out.println(c.dump());
            } else {
                System.out.println("Unknown option.");
            }
        }
    }
}