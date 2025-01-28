/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.model;

import fi.tuni.sd.newsapp.datamodels.Country;
import fi.tuni.sd.newsapp.enums.ArticleLanguageEnum;
import fi.tuni.sd.newsapp.enums.ObserverUpdateType;
import fi.tuni.sd.newsapp.util.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Data model for managing country-related information.
 * <p>
 * This class maintains a list of countries and their related information. It allows filtering countries based
 * on user input and manages the currently selected country. It also identifies the most appropriate language 
 * for news articles based on the selected country.
 * <p>
 * The class extends {@link Observable} to notify observers about changes to the country list or the selected
 * country.
 * 
 * @author Vaccari, Savidya
 */
public class CountryModel extends Observable {

    private final List<Country> countries = new ArrayList<>();
    private final ObservableList<String> filteredCountries = FXCollections.observableArrayList();
    private Country selectedCountry;
    private ArticleLanguageEnum selectedCountryLanguage;

    /**
     * Retrieves the list of all countries.
     *
     * @return List of Country objects.
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * Sets the list of countries and notifies observers of the update.
     *
     * @param countries The list of Country objects.
     */
    public void setCountries(List<Country> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        notifyObservers(ObserverUpdateType.COUNTRY_LIST_UPDATED);
    }

    /**
     * Retrieves the list of filtered countries.
     *
     * @return List of filtered Country objects.
     */
    public ObservableList<String> getFilteredCountries() {
        return filteredCountries;
    }

    /**
     * Sets the list of filtered countries and notifies observers of the update.
     *
     * @param filteredCountries The list of filtered countries.
     */
    public void setFilteredCountries(ObservableList<String> filteredCountries) {
        this.filteredCountries.setAll(filteredCountries);
        notifyObservers(ObserverUpdateType.FILTERED_COUNTRY_LIST_UPDATED);
    }

    /**
     * Filters the list of countries based on user input.
     *
     * @param userInput The input provided by the user.
     */
    public void getFilteredOrAllCountries(String userInput) {
        setFilteredCountries(FXCollections.observableArrayList(countries.stream()
                .map(country -> country.getName().getCommon())
                .filter(countryName -> userInput == null || userInput.isEmpty() || countryName.toLowerCase().contains(userInput.toLowerCase()))
                .collect(Collectors.toList())));
    }

    /**
     * Gets the country by its name.
     *
     * @param name The name of the country to get.
     */
    public void getCountryByName(String name) {
        Country country = countries.stream()
                .filter(c -> c.getName().getCommon().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElse(null);

        if (country != null) {
            setSelectedCountry(country);
            findNewsLanguage();
            notifyObservers(ObserverUpdateType.COUNTRY_SELECTED_UPDATED);
        }
    }

    /**
     * Retrieves the selected country.
     *
     * @return The selected Country.
     */
    public Country getSelectedCountry() {
        return selectedCountry;
    }

    /**
     * Sets the currently selected country.
     *
     * @param selectedCountry The selected Country.
     */
    private void setSelectedCountry(Country selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    /**
     * Finds the most appropriate language from the given country's languages
     * that matches the supported {@link ArticleLanguageEnum}. If no exact match
     * is found the default language {@code ENGLISH} is set.
     */
    private void findNewsLanguage() {
        // Get the languages map from the country
        Map<String, String> languages = selectedCountry.getLanguages();

        for (Map.Entry<String, String> entry : languages.entrySet()) {
            String languageName = entry.getValue(); // Get the language name

            // Check if the language name matches any of the ArticleLanguageEnum constants
            for (ArticleLanguageEnum languageEnum : ArticleLanguageEnum.values()) {
                // Check if the enum name is contained within the language name
                if (languageName.toLowerCase().contains(languageEnum.name().toLowerCase())) {
                    setSelectedCountryLanguage(languageEnum);
                    return;
                }
            }
        }
        // If no match is found, default to ENGLISH
        setSelectedCountryLanguage(ArticleLanguageEnum.ENGLISH);
    }

    /**
     * Gets the selected country's language that matches the supported
     * {@link ArticleLanguageEnum}.
     *
     * @return the selectedCountryLanguage
     */
    public ArticleLanguageEnum getSelectedCountryLanguage() {
        return selectedCountryLanguage;
    }

    /**
     * Sets the selected country's language that matches the supported
     * {@link ArticleLanguageEnum}.
     *
     * @param selectedCountryLanguage the selectedCountryLanguage to set
     */
    private void setSelectedCountryLanguage(ArticleLanguageEnum selectedCountryLanguage) {
        this.selectedCountryLanguage = selectedCountryLanguage;
    }

}
