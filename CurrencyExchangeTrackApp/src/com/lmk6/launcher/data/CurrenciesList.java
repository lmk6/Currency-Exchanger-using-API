package com.lmk6.launcher.data;

import java.util.*;

import static com.lmk6.launcher.data.ConstValues.*;

public class CurrenciesList {
    ArrayList<CurrencyClass> currencies;

    public CurrenciesList() {
        currencies = new ArrayList<>();
        makeCurrencyList();
    }

    private void makeCurrencyList() {                       //Based on data available in the file
        JSONParser jsonParser = new JSONParser();
        Map<String, ?> dataMap = jsonParser.getRatesInMap(FILENAME);
        dataMap.forEach((k, v) -> currencies.add(new CurrencyClass(k, v)));
        currencies.sort(Comparator.comparing(CurrencyClass::getCode));
    }

    public ArrayList<CurrencyClass> getCurrencyList() {
        return currencies;
    }

    public ArrayList<String> getListOfCurrNames() {
        ArrayList<String> names = new ArrayList<>();
        for (CurrencyClass c : currencies) {
            if (c.getName() != null) names.add(c.getName());
        }
        return names;
    }

    public ArrayList<String> getListOfCurrCodes() {
        ArrayList<String> codes = new ArrayList<>();
        for (CurrencyClass c : currencies) {
            if (c.getCode() != null) codes.add(c.getCode());
        }
        return codes;
    }
}
