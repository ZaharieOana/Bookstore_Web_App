package com.example.Bookstore.constants;

import java.util.HashMap;
import java.util.Map;

public class Currency {
    private static final Double RON = 1.0;
    private static final Double EURO = 0.2;
    private static final Double DOLLAR = 0.21;

    private static Currency instance;

    private Currency(){}

    public static Currency getInstance(){
        if(instance == null)
            instance = new Currency();
        return instance;
    }
    public Map<String, Double> getCurrency(){
        Map<String, Double> curr = new HashMap<>();
        curr.put("RON", RON);
        curr.put("EURO", EURO);
        curr.put("DOLLAR", DOLLAR);
        return curr;
    }
}
