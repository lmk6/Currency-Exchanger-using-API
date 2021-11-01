package com.lmk6.launcher.data;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class DataReceiver {

    private String currency;
    private String urlM;
    private String apikey = "ce873770-34da-11ec-aba6-bb2ddf8ec78b";
    private JSONParser jsonParser;
    private Map dataMap;
    String filename = "currencyExchangeRates.json";

    public DataReceiver() {
        currency = "USD";
        urlM = "latest";
        jsonParser = new JSONParser();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void getSpecificExchangeRate() {
        urlM = "latest?apikey=" + apikey + "&base_currency=" + currency;
        requestExchangeRates();
        dataMap = jsonParser.getRatesInMap(filename);
        dataMap.forEach((k,v) -> System.out.println("Currency: " + k + ", value = " + v));
    }

    public void getLatestExchangeRate() {
        urlM = "latest";
    }

    public void getHistoricalExchangeRate(String from_date, String to_date) { //yyyy-mm-dd
        urlM = "historical?apikey=" + apikey + "&base_currency=" + currency + "&date_from=" + from_date + "&date_to=" + to_date;
        requestExchangeRates();
    }

    private void requestExchangeRates() {
        try {
            String url = "https://freecurrencyapi.net/api/v2/" + urlM;
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();


            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                File myObj = new File(filename);
                Writer writer = new FileWriter(myObj);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                System.out.println(response.toString());
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
