/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.util.service;

import fi.tuni.sd.newsapp.util.Constant;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for API related utility functions.
 * 
 * This class provides utility methods to generate URLs for fetching country data
 * and top news articles from a news API. Additionally, it retrieves the news API key
 * from a configuration file for authentication with the API.
 *
 * @author Savidya
 */
public class APIUtil {

    /**
     * Utility method to get the news API key.
     *
     * 
     * @return the news API key stored in the properties file.
     */
    public static String getApiKey() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(Constant.API_KEY_CONFIG_FILE_PATH)) {
            properties.load(input);
            return properties.getProperty("news.api.key");
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Utility method to get the URL for fetching country data.
     *
     * @return the URL to fetch all country data.
     */
    public static String getFetchAllCountriesUrl() {
        return String.format(Constant.FETCH_All_COUNTRIES_URL, Constant.COUNTRY_BASE_URL);
    }

    /**
     * Utility method to get the URL for fetching top news articles related to a
     * specific country.
     *
     * @param language
     * @param countryCode Country code of the searched country.
     * @param NewsCategory
     * @param fromDate
     * @param toDate
     * @return the URL to fetch news articles.
     */
    public static String getFetchAllNewsUrl(
            String language, 
            String countryCode, 
            String NewsCategory, 
            String fromDate, 
            String toDate) {
        return String.format(
                Constant.FETCH_All_NEWS_URL, // Next parameters are replaced into first parameter String
                Constant.NEWS_BASE_URL, 
                language, 
                countryCode, 
                NewsCategory, 
                fromDate, 
                toDate,
                getApiKey());
    }
    
}
