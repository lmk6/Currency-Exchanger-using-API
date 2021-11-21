package com.lmk6.launcher.data;

import java.util.Currency;

public class CurrencyClass {
    private final String code;
    private double value;
    private String name;

    public CurrencyClass(Object code, Object value) {
        this.code = (String) code;
        try {
            this.value = (double) value;
        } catch (ClassCastException e) {
            this.value = (double) (Integer) value;
        }
        try {
            name = Currency.getInstance(this.code).getDisplayName();
        } catch (IllegalArgumentException e) {
            name = (String) code;
        }
    }

    public String getCode() {
        return code;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
