/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.util;

/**
 * The interface class that defines country-related data retrieval methods from
 * the country API.
 *
 * @author Savidya
 */
public interface ICountryDataProvider {

    /**
     * Fetches the list of countries from the country API.
     *
     * @throws Exception if an error occurs during the request.
     */
    public void fetchAllCountries() throws Exception;
    
}
