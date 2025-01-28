/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.sd.newsapp.util.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for APIUtil class.
 *
 * @author Savidya
 */
public class APIUtilTest {

    /**
     * Test of getFetchAllCountriesUrl method, of class APIUtil.
     */
    @Test
    public void testGetFetchAllCountriesUrl() {
        String expectedUrl = "https://restcountries.com/v3.1/all?fields=name,cca2,flags,latlng,region,capital,area,currencies,languages,population";

        String actualUrl = APIUtil.getFetchAllCountriesUrl();

        assertEquals(expectedUrl, actualUrl, "The generated URL for fetching all countries should match the expected URL.");
    }

    /**
     * Test of getFetchAllNewsUrl method, of class APIUtil.
     */
    @Test
    public void testGetFetchAllNewsUrl() {
        String language = "en";
        String countryCode = "it";
        String category = "GENERAL";
        String fromDate = "2024-11-03T00:00:01Z";
        String toDate = "2024-11-10T23:59:59Z";
        String apiKey = APIUtil.getApiKey();

        String expectedUrl = String.format(
                "https://gnews.io/api/v4/top-headlines?lang=%s&country=%s&category=%s&from=%s&to=%s&max=10&apikey=%s",
                language, countryCode, category, fromDate, toDate, apiKey
        );

        String actualUrl = APIUtil.getFetchAllNewsUrl(language, countryCode, category, fromDate, toDate);

        assertEquals(expectedUrl, actualUrl, "The generated URL for fetching all news should match the expected URL.");
    }

}
