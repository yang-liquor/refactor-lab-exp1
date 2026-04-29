package com.refactorlab.exp1.util;

import java.util.Map;

public class TextTable {
    public static String mapToText(Map<String, ?> map) {
        StringBuilder sb = new StringBuilder();
        for (String k : map.keySet()) {
            sb.append(k).append(" => ").append(map.get(k)).append("\n");
        }
        return sb.toString();
    }
}