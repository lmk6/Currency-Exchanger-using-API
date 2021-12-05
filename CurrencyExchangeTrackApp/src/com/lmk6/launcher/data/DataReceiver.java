package com.lmk6.launcher.data;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import static com.lmk6.launcher.data.ConstValues.*;

public class DataReceiver {

    private String currency;
    private String urlM;
    private final String apikey = "ce873770-34da-11ec-aba6-bb2ddf8ec78b";
    private final JSONParser jsonParser;
    private Map<String, Double> exchangeRatesMap;
    private Map<String, Double> previousExchangeRatesMap;

    public DataReceiver() {
        currency = "USD";
        jsonParser = new JSONParser();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Map<String, Double> getSpecificExchangeRate() {
        urlM = "latest?apikey=" + apikey + "&base_currency=" + currency;
        requestExchangeRates();
        previousExchangeRatesMap = exchangeRatesMap;
        exchangeRatesMap = jsonParser.getRatesInMap(FILENAME);
        //dataMap.forEach((k,v) -> System.out.println("Currency: " + k + ", value = " + v));
        return exchangeRatesMap;
    }

    public Map<String, Double> getLatestExchangeRate() {
        urlM = "latest";
        requestExchangeRates();
        return jsonParser.getRatesInMap(FILENAME);
    }

    public Map<String, ValueChangeFlag> getFlag() {
        Map<String, ValueChangeFlag> result = new HashMap<>();
        if(previousExchangeRatesMap != null) {
            exchangeRatesMap.forEach((k, v) -> {
                if(v - previousExchangeRatesMap.get(k) > 0) result.put(k, ValueChangeFlag.UP);
                else if(v - previousExchangeRatesMap.get(k) < 0) result.put(k, ValueChangeFlag.DOWN);
                else result.put(k, ValueChangeFlag.STILL);
            });
            return result;
        } else return null;
    }

/*    public Map<String, ?> getHistoricalExchangeRate(String from_date, String to_date, String exchangeCurrency) { //yyyy-mm-dd
        urlM = "historical?apikey=" + apikey + "&base_currency=" + currency + "&date_from=" + from_date + "&date_to=" + to_date;
        requestExchangeRates();
        dataMap = jsonParser.getRatesInMap(FILENAME);
        Map<String, ?> historicDataMap = new HashMap<>();
        System.out.println(dataMap.get(exchangeCurrency));
        return historicDataMap;
    }*/

    private void requestExchangeRates() {
        try {
            String url = "https://freecurrencyapi.net/api/v2/" + urlM;
            URL urlForGetRequest = new URL(url);
            String readLine;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();


            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                File myObj = new File(FILENAME);
                Writer writer = new FileWriter(myObj);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                //System.out.println(response.toString());
                JSONObject result = new JSONObject(response.toString());
                bufferedWriter.write(result.toString(2));
                bufferedWriter.close();
            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
