/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.datamodels;

/**
 * Data model representing user preferences for filtering news articles.
 * 
 * This class encapsulates the user's preferences, including the preferred country,
 * category, language, and date range for retrieving news articles. It provides 
 * a simple, immutable structure for managing user-specific settings.
 * 
 * Fields include:
 * - Country code representing the user's preferred country.
 * - Category of news articles preferred by the user.
 * - Language code for the user's preferred language of news articles.
 * - Date range specifying the start and end dates for news articles.
 * 
 * @author Savidya
 */
public class UserData {

    /**
     * The country code representing the user's preferred country.
     */
    private final String country;

    /**
     * The category of news articles preferred by the user.
     */
    private final String category;

    /**
     * The language code for the user's preferred language of news articles.
     */
    private final String language;

    /**
     * The start date for the preferred news article range.
     */
    private final String fromDate;

    /**
     * The end date for the preferred news article range.
     */
    private final String toDate;

    /**
     * Constructs a new UserData instance.
     *
     * @param country The country code representing the user's preferred
     * country.
     * @param category The category of news articles preferred by the user.
     * @param language The language code for the preferred language of news
     * articles.
     * @param fromDate The start date for the preferred news article range.
     * @param toDate The end date for the preferred news article range.
     */
    public UserData(String country, String category, String language, String fromDate, String toDate) {
        this.country = country;
        this.category = category;
        this.language = language;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

     /**
     * Retrieves the preferred country code.
     *
     * @return the country code as a {@code String}.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Retrieves the preferred category of news articles.
     *
     * @return the category as a {@code String}.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Retrieves the preferred language code.
     *
     * @return the language code as a {@code String}.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Retrieves the start date for the preferred news article range.
     *
     * @return the start date as a {@code String}.
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * Retrieves the end date for the preferred news article range.
     *
     * @return the end date as a {@code String}.
     */
    public String getToDate() {
        return toDate;
    }

}
