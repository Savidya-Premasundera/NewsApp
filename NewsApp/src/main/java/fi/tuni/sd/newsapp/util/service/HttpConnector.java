/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.util.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of IHttpConnector interface using HttpURLConnection with the
 * AbstractHttpConnector extended for common utility methods.
 *
 * @author Savidya
 */
public class HttpConnector extends AbstractHttpConnector {
    /**
     * Constructor to initialize with a Gson object for JSON
     * serialization/deserialization.
     */
    public HttpConnector() {
        super(new Gson());
    }

    /**
     * Performs a HTTP GET request to the specified URL and returns the response
     * in the required response type.
     *
     * @param <T> Type of the response required.
     * @param url The URL to send the GET request.
     * @param responseType The response type for deserialization.
     * @return The deserialized response object.
     * @throws IOException if the GET request fails or any error occurs.
     */
    @Override
    public <T> T get(String url, Type responseType) throws IOException {
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            int responseCode = con.getResponseCode();
            checkResponseCode(responseCode, con.getResponseMessage());

            String response = readResponse(con.getInputStream());
            return gson.fromJson(response, responseType);

        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    /**
     * Performs a HTTP POST request to the specified URL, sending the provided
     * request data in JSON format.
     *
     * @param <T> Type of the response required.
     * @param url The URL to send the POST request.
     * @param requestData The data to be sent in the POST request body.
     * @param responseType The response type for deserialization.
     * @return The deserialized response object.
     * @throws IOException if the POST request fails or any error occurs.
     */
    @Override
    public <T> T post(String url, JsonObject requestData, Type responseType) throws IOException {
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                String json = gson.toJson(requestData);
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            checkResponseCode(responseCode, con.getResponseMessage());

            String response = readResponse(con.getInputStream());
            return gson.fromJson(response, responseType);

        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
    
}
