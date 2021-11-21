package com.lmk6.launcher;

import com.lmk6.launcher.data.DataReceiver;

import java.util.Currency;

public class Launcher {
    static DataReceiver dataReceiver = new DataReceiver();
    public static void main(String[] args) {
        dataReceiver.setCurrency("EUR");
        dataReceiver.getSpecificExchangeRate();
    }
}

