package com.refactorlab.exp1.util;

import java.util.Random;

public class IdUtil {
    public static String randomId() {
        Random r = new Random();
        int x = r.nextInt(999999);
        return "ORD-" + x;
    }
}