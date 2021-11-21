package com.lmk6.launcher.data;
import java.util.ArrayList;
import java.util.Map;

import static com.lmk6.launcher.data.ConstValues.*;

public class CurrenciesList {
    ArrayList<CurrencyClass> currencies;

    public CurrenciesList() {

    }

    private void getCurrencyList() {
        JSONParser jsonParser = new JSONParser();
        Map dataMap = jsonParser.getRatesInMap(FILENAME);
        dataMap.forEach((k,v) -> currencies.add(new CurrencyClass(k, v)));
    }
}
