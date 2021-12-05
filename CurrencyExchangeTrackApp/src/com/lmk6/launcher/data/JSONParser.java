package com.lmk6.launcher.data;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JSONParser {
    public JSONParser() {

    }

    public Map<String, Double> getRatesInMap(String filename) {
        try {
            File input = new File(filename);
            Map<String, Object> mapFromJson = new ObjectMapper().readValue(input, new TypeReference<Map<String, Double>>() {
            });
            return (Map<String, Double>) mapFromJson.get("data");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
