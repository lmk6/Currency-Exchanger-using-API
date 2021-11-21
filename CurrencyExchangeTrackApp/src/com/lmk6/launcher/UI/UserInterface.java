package com.lmk6.launcher.UI;

import com.lmk6.launcher.data.CurrenciesList;
import com.lmk6.launcher.data.DataReceiver;

public class UserInterface {
    public UserInterface() {
        DataReceiver dataReceiver = new DataReceiver();
        dataReceiver.getSpecificExchangeRate();
        CurrenciesList c = new CurrenciesList();
        System.out.println(c.getListOfCurrCodes().toString());
    }


}
