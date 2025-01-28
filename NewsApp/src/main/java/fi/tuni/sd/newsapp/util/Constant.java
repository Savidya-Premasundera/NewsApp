/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.util;

/**
 * Constants used throughout the app.
 *
 * @author Savidya
 */
public class Constant {

    /**
     * Base URL of the Country API.
     */
    public static final String COUNTRY_BASE_URL = "https://restcountries.com/v3.1/";

    /**
     * Base URL of the News API.
     */
    public static final String NEWS_BASE_URL = "https://gnews.io/api/v4/";

    /**
     * File path of the config file containing the API key.
     */
    public static final String API_KEY_CONFIG_FILE_PATH = "config.properties";

    /**
     * File path of the file containing last saved user preferences.
     */
    public static final String USER_PREFS_FILE_PATH = "userData.json";

    // App URLs
    /**
     * URL to fetch all countries.
     */
    public static final String FETCH_All_COUNTRIES_URL = "%sall?fields=name,cca2,flags,latlng,region,capital,area,currencies,languages,population";

    /**
     * URL to fetch news articles for a specific country.
     */
    public static final String FETCH_All_NEWS_URL = "%stop-headlines?lang=%s&country=%s&category=%s&from=%s&to=%s&max=10&apikey=%s";

    /**
     * Default suffix used for the from date.
     */
    public static final String FROM_DATE_SUFFIX = "T00:00:01Z";

    /**
     * Default suffix used for the to date.
     */
    public static final String TO_DATE_SUFFIX = "T23:59:59Z";

}
