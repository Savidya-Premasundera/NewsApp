/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.util;

/**
 * The interface class that defines news-related data retrieval methods from the
 * news API.
 *
 * @author Savidya
 */
public interface INewsDataProvider {
    
    /**
     * Retrieves top news for user filters.
     *
     * @param countryCode A 2-letter ISO 3166-1 alpha-2 country code.
     * @param NewsCategory This parameter allows you to change the category for
     * the request. The available categories are : general, world, nation,
     * business, technology, entertainment, sports, science and health.
     * @param language The language of the news articles required.
     * @param fromDate From date to filter the articles.
     * @param toDate To date to filter the articles.
     * @throws Exception if an error occurs during the request.
     */
    public void fetchTopNewsForCountry(
            String countryCode,
            String NewsCategory, 
            String language, 
            String fromDate,
            String toDate) throws Exception;
    
}
