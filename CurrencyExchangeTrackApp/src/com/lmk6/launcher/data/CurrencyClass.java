package com.lmk6.launcher.data;

import java.util.Currency;

public class CurrencyClass {
    private final String code;
    private final float value;
    private final String name;

    public CurrencyClass(Object code, Object value) {
        this.code = (String) code;
        this.value = (float) value;
        name = Currency.getInstance(this.code).getDisplayName();
    }

    public String getCode() {
        return code;
    }

    public float getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
