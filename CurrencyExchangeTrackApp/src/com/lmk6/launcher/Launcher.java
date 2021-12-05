package com.lmk6.launcher;

import com.lmk6.launcher.data.DataReceiver;

import java.util.Currency;

public class Launcher {
    public static void main(String[] args) {
        DataReceiver d = new DataReceiver();
        System.out.println(d.getSpecificExchangeRate());
    }
}

