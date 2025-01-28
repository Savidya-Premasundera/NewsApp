/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.datamodels;

import java.util.List;
import java.util.Map;

/**
 * Data model representing a country
 *
 * @author Savidya
 */
public class Country {

    private Flag flags;
    private Name name;
    private String cca2;
    private List<Double> latlng;
    private List<String> capital;
    private String region;
    private Map<String, Currency> currencies;
    private Map<String, String> languages;
    private double population;
    private double area;

    /**
     * Constructs a new {@code Country} with the specified attributes.
     *
     * @param flags The flags associated with the country, represented as a
     * {@link Flag} object.
     * @param name The name of the country, represented as a {@link Name}
     * object.
     * @param cca2 The ISO 3166-1 alpha-2 code of the country.
     * @param latlng The geographical coordinates of the country (latitude and
     * longitude) as a list of doubles.
     * @param capital A list of the capital cities of the country.
     * @param region The region to which the country belongs.
     * @param currencies A map of the country's currencies, where the key is the
     * currency code, and the value is a {@link Currency} object.
     * @param languages A map of the languages spoken in the country, where the
     * key is the language code, and the value is the language name.
     * @param population The total population of the country.
     * @param area The total area of the country.
     */
    public Country(Flag flags, Name name, String cca2, List<Double> latlng, List<String> capital, String region,
            Map<String, Currency> currencies, Map<String, String> languages, double population,
            double area) {
        this.flags = flags;
        this.name = name;
        this.cca2 = cca2;
        this.latlng = latlng;
        this.capital = capital;
        this.region = region;
        this.currencies = currencies;
        this.languages = languages;
        this.population = population;
        this.area = area;
    }

    /**
     * Gets the flags of the country.
     *
     * @return The flags
     */
    public Flag getFlags() {
        return flags;
    }

    /**
     * Gets the name of the country.
     *
     * @return The name
     */
    public Name getName() {
        return name;
    }

    /**
     * Get the ISO 3166-1 alpha-2 code (cca2) of the country.
     *
     * @return The country code
     */
    public String getCca2() {
        return cca2;
    }

    /**
     * Gets the geographical coordinates (latitude and longitude) of the
     * country.
     *
     * @return The coordinates
     */
    public List<Double> getLatlng() {
        return latlng;
    }

    /**
     * Gets the capital cities of the country.
     *
     * @return The capitals
     */
    public List<String> getCapital() {
        return capital;
    }

    /**
     * Gets the region of the country.
     *
     * @return The region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Gets the currencies of the country.
     *
     * @return The currencies
     */
    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    /**
     * Gets the languages spoken in the country.
     *
     * @return The languages
     */
    public Map<String, String> getLanguages() {
        return languages;
    }

    /**
     * Gets the population of the country.
     *
     * @return The population
     */
    public double getPopulation() {
        return population;
    }

    /**
     * Gets the area of the country.
     *
     * @return The area
     */
    public double getArea() {
        return area;
    }
    
}
