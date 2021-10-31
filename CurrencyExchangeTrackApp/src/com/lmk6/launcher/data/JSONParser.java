package com.lmk6.launcher.data;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JSONParser {
    public JSONParser() {

    }

    public Map getRatesInMap(String filename) {
        try {
            File input = new File("currencyExchangeRates.json");
            Map<String, Object> mapFromJson = new ObjectMapper().readValue(input, new TypeReference<Map<String, Object>>() {
            });
            Map<String, Object> ratesMap = (Map<String, Object>) mapFromJson.get("data");
            return ratesMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
