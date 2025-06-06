package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CurrencyExchangeService {

    private static final String API_KEY = "31b2f77fe9a0232bc98f6838";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public double getUsdToCopRate() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((java.io.InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            JsonObject rates = jsonobj.getAsJsonObject("conversion_rates");
            return rates.get("COP").getAsDouble();
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo la tasa de cambio USD/COP", e);
        }
    }
}
