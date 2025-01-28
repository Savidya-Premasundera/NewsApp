/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.util.service;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * An abstract class with utility methods for HTTP requests.
 *
 * @author Savidya
 */
public abstract class AbstractHttpConnector implements IHttpConnector {

    protected final Gson gson;

    /**
     * Constructor to initialize Gson object for JSON
     * serialization/deserialization.
     *
     * @param gson Gson instance used for serializing/deserializing JSON data.
     */
    public AbstractHttpConnector(Gson gson) {
        this.gson = gson;
    }

    /**
     * Utility method to read response from InputStream.
     *
     * @param inputStream Input stream from the HTTP response.
     * @return A string containing the HTTP response.
     * @throws IOException if an error occurs while reading the input stream.
     */
    protected String readResponse(InputStream inputStream) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    /**
     * Utility method to check response code and throw appropriate exceptions.
     *
     * @param responseCode The HTTP response code.
     * @param responseMessage The HTTP response message.
     * @throws IOException if the response code indicates a failure (not HTTP OK
     * (200)).
     */
    protected void checkResponseCode(int responseCode, String responseMessage) throws IOException {
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException(String.format("Request failed: %d %s", responseCode, responseMessage));
        }
    }
    
}
