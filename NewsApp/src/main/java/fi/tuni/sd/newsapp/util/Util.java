/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.util;

import fi.tuni.sd.newsapp.enums.ArticleCategoryEnum;
import fi.tuni.sd.newsapp.enums.ArticleLanguageEnum;
import fi.tuni.sd.newsapp.model.CountryModel;
import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * Utility methods
 *
 * @author Savidya
 */
public class Util {

    /**
     * Retrieves the name of the selected country.
     *
     * @param countryModel Country model
     * @return The common name of the selected country or a default value if
     * unavailable.
     */
    public static String getCountryName(CountryModel countryModel) {
        return (countryModel.getSelectedCountry() != null && countryModel.getSelectedCountry().getName() != null)
                ? countryModel.getSelectedCountry().getName().getCommon()
                : "Unknown Country";
    }

    /**
     * Retrieves the country code (CCA2) of the selected country.
     *
     * @param countryModel Country model
     * @return The CCA2 code of the selected country or a default value if
     * unavailable.
     */
    public static String getCountryCode(CountryModel countryModel) {
        return (countryModel.getSelectedCountry() != null && countryModel.getSelectedCountry().getCca2() != null)
                ? countryModel.getSelectedCountry().getCca2()
                : "Unknown Code";
    }

    /**
     * Retrieves the selected category from the category drop-down.
     *
     * @param categoryDropdown Category drop-down
     * @return The selected category or a default value if none is selected.
     */
    public static String getCategory(ComboBox<ArticleCategoryEnum> categoryDropdown) {
        return (categoryDropdown.getValue() != null)
                ? categoryDropdown.getValue().name()
                : !categoryDropdown.getItems().isEmpty() ? categoryDropdown.getItems().get(0).name() : ArticleCategoryEnum.GENERAL.name();
    }

    /**
     * Retrieves the selected language from the language drop-down.
     *
     * @param languageDropdown Language drop-down
     * @return The selected language or a default value if none is selected.
     */
    public static String getLanguage(ComboBox<ArticleLanguageEnum> languageDropdown) {
        return (languageDropdown.getValue() != null)
                ? languageDropdown.getValue().name()
                : !languageDropdown.getItems().isEmpty() ? languageDropdown.getItems().get(0).name() : ArticleLanguageEnum.ENGLISH.name();
    }

    /**
     * Retrieves the language code of the selected language from the language
     * drop-down.
     *
     * @param languageDropdown Language drop-down
     * @return The language code of the selected language or a default value if
     * none is selected.
     */
    public static String getLanguageCode(ComboBox<ArticleLanguageEnum> languageDropdown) {
        return (languageDropdown.getValue() != null)
                ? languageDropdown.getValue().getCode()
                : !languageDropdown.getItems().isEmpty() ? languageDropdown.getItems().get(0).getCode() : ArticleLanguageEnum.ENGLISH.getCode();
    }

    /**
     * Retrieves the selected from date from the date picker.
     *
     * @param calendarDateFrom From date picker
     * @return The selected from date or a default value if none is selected.
     */
    public static String getFromDate(DatePicker calendarDateFrom) {
        return (calendarDateFrom.getValue() != null)
                ? calendarDateFrom.getValue().toString()
                : LocalDate.now().minusWeeks(1).toString();
    }

    /**
     * Retrieves the selected to date from the date picker. If no date is
     * selected, defaults to the current date.
     *
     * @param calendarDateTo To date picker
     * @return The selected to date or a default value if none is selected.
     */
    public static String getToDate(DatePicker calendarDateTo) {
        return (calendarDateTo.getValue() != null)
                ? calendarDateTo.getValue().toString()
                : LocalDate.now().toString();
    }

}
