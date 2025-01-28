/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.sd.newsapp.util.service;

import com.google.gson.Gson;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AbstractHttpConnector class.
 *
 * @author Savidya
 */
public class AbstractHttpConnectorTest {

    private AbstractHttpConnector abstractHttpConnector;

    @BeforeEach
    void setUp() {
        abstractHttpConnector = new AbstractHttpConnector(new Gson()) {
            @Override
            public <T> T get(String url, java.lang.reflect.Type responseType) throws IOException {
                return null;
            }

            @Override
            public <T> T post(String url, com.google.gson.JsonObject requestData, java.lang.reflect.Type responseType) throws IOException {
                return null;
            }
        };
    }

    @Test
    void testReadResponseSuccess() throws IOException {
        String mockResponse = "This is a test response";
        InputStream mockInputStream = new ByteArrayInputStream(mockResponse.getBytes());

        String result = abstractHttpConnector.readResponse(mockInputStream);

        assertEquals(mockResponse, result, "Response should match");
    }

    @Test
    void testReadResponseEmptyStream() throws IOException {
        InputStream emptyStream = new ByteArrayInputStream(new byte[0]);

        String result = abstractHttpConnector.readResponse(emptyStream);

        assertEquals("", result, "Response should be an empty string");
    }

    @Test
    void testCheckResponseCodeSuccess() {
        assertDoesNotThrow(() -> abstractHttpConnector.checkResponseCode(HttpURLConnection.HTTP_OK, "OK"),
                "Should not throw an exception for HTTP OK");
    }

    @Test
    void testCheckResponseCodeFailure() {
        IOException exception = assertThrows(IOException.class, () -> {
            abstractHttpConnector.checkResponseCode(HttpURLConnection.HTTP_BAD_REQUEST, "Bad Request");
        });
        assertTrue(exception.getMessage().contains("400 Bad Request"), "Exception message should include response code and message");
    }

}
