package com.lmk6.launcher;

import com.lmk6.launcher.data.DataReceiver;

public class Launcher {
    static DataReceiver dataReceiver = new DataReceiver();
    public static void main(String[] args) {
        dataReceiver.setCurrency("EUR");
        dataReceiver.getHistoricalExchangeRate("2020-01-03", "2021-11-01");
    }
}

