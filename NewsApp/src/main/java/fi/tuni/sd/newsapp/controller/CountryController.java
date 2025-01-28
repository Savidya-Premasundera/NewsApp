/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.controller;

import com.google.gson.reflect.TypeToken;
import fi.tuni.sd.newsapp.datamodels.Country;
import fi.tuni.sd.newsapp.model.CountryModel;
import fi.tuni.sd.newsapp.util.ICountryDataProvider;
import static fi.tuni.sd.newsapp.util.service.APIUtil.getFetchAllCountriesUrl;
import fi.tuni.sd.newsapp.util.service.IHttpConnector;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller class responsible for managing country-related actions and
 * operations.
 *
 * This class acts as an intermediary between the view and the model for
 * country-related functionality. It provides methods to fetch, filter, and
 * retrieve countries from the country model. It also interacts with external
 * services to fetch country data via HTTP requests.
 *
 * @author Savidya
 */
public class CountryController implements ICountryDataProvider {

    /**
     * The model class that holds the country data.
     */
    private final CountryModel countryModel;

    /**
     * The HTTP connector used to make HTTP requests to external services.
     */
    private final IHttpConnector httpConnector;

    /**
     * Constructor that initializes the CountryController with the specified
     * {@code CountryModel}. It also initializes the {@code HttpConnector} to
     * facilitate HTTP requests.
     *
     * @param countryModel The country model class used to store and manage
     * country data.
     * @param httpConnector The HTTP connector used to make HTTP requests to
     * external APIs.
     */
    public CountryController(CountryModel countryModel, IHttpConnector httpConnector) {
        this.countryModel = countryModel;
        this.httpConnector = httpConnector;
    }

    /**
     * Fetches the list of all countries from the country API and updates the
     * country model.
     *
     * This method makes an HTTP request to an external service to retrieve the
     * full list of countries. The retrieved list is then set in the
     * {@code CountryModel}. If no countries are retrieved, an empty list is set
     * in the model.
     *
     * @throws Exception if an error occurs during the HTTP request or while
     * processing the response.
     */
    @Override
    public void fetchAllCountries() throws Exception {
        List<Country> countries = httpConnector.get(getFetchAllCountriesUrl(), new TypeToken<List<Country>>() {
        }.getType());
        if (countries != null) {
            countryModel.setCountries(countries);
        } else {
            countryModel.setCountries(new ArrayList<>());
        }
    }

    /**
     * Filters the list of countries by the given name.
     *
     * This method updates the {@code CountryModel}
     *
     * @param name The name of the country to filter by. This can be a full or
     * partial name.
     */
    public void filterCountriesByName(String name) {
        countryModel.getFilteredOrAllCountries(name);
    }

    /**
     * Retrieves a country by its name.
     *
     * This method retrieves the details of a country from the
     * {@code CountryModel} using the provided country name.
     *
     * @param name The name of the country to retrieve.
     */
    public void getCountryByName(String name) {
        countryModel.getCountryByName(name);
    }

}
