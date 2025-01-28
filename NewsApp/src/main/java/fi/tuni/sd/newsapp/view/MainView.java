/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.view;

import fi.tuni.sd.newsapp.datamodels.UserData;
import fi.tuni.sd.newsapp.enums.ObserverUpdateType;
import fi.tuni.sd.newsapp.model.CountryModel;
import fi.tuni.sd.newsapp.controller.CountryController;
import fi.tuni.sd.newsapp.controller.NewsController;
import fi.tuni.sd.newsapp.controller.UserDataController;
import fi.tuni.sd.newsapp.datamodels.Country;
import fi.tuni.sd.newsapp.enums.ArticleCategoryEnum;
import fi.tuni.sd.newsapp.enums.ArticleLanguageEnum;
import fi.tuni.sd.newsapp.model.NewsModel;
import fi.tuni.sd.newsapp.model.UserDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import fi.tuni.sd.newsapp.util.IObserver;
import fi.tuni.sd.newsapp.util.Util;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;

/**
 * Main view of the application.
 *
 * This class handles the main user interface and interaction logic for managing
 * countries, news, and user preferences. It observes updates from various
 * models (CountryModel, NewsModel, UserDataModel) and updates the UI
 * accordingly. The class also handles user inputs such as search, country
 * selection, and news category filtering.
 *
 * @author Savidya
 */
public class MainView implements IObserver {

    private CountryController countryController;
    private NewsController newsController;
    private UserDataController userDataController;
    private CountryModel countryModel;
    private NewsModel newsModel;
    private UserDataModel userDataModel;
    private boolean isUpdatingFromPreferences;

    @FXML
    private WebView mapView;

    @FXML
    private TextField countrySearchBox;

    @FXML
    private ListView<String> filteredCountries;

    @FXML
    private VBox countryInfo;

    @FXML
    private VBox newsList;

    @FXML
    private ComboBox<ArticleCategoryEnum> categoryDropdown;

    @FXML
    private ComboBox<ArticleLanguageEnum> languageDropdown;

    @FXML
    private DatePicker calendarDateFrom;

    @FXML
    private DatePicker calendarDateTo;

    @FXML
    private ProgressIndicator loadingIndicator;

    /**
     * Initializes view elements.
     */
    @FXML
    public void initialize() {
        Locale.setDefault(Locale.ENGLISH);
        categoryDropdown.setItems(FXCollections.observableArrayList(ArticleCategoryEnum.values()));
        categoryDropdown.getSelectionModel().selectFirst();

        languageDropdown.setItems(FXCollections.observableArrayList(ArticleLanguageEnum.values()));
        languageDropdown.getSelectionModel().selectFirst();

        LocalDate currentDate = LocalDate.now();
        calendarDateFrom.setValue(currentDate.minusWeeks(1));
        calendarDateTo.setValue(currentDate);

        attachListeners();
    }

    /**
     * Event handler for the search box. Filters the country list as the user
     * types.
     */
    @FXML
    private void onSearch() {
        var userInputText = countrySearchBox.getText().toLowerCase();
        countryController.filterCountriesByName(userInputText);
    }

    /**
     * Event handler for selecting a country from the list.
     */
    @FXML
    private void selectCountry() {
        // Remove popup of filtered countries
        filteredCountries.setVisible(false);
        // Set the selected country name in the search box
        String selectedCountryName = filteredCountries.getSelectionModel().getSelectedItem();
        if (selectedCountryName != null) {
            // Get the selected country object by country name
            countryController.getCountryByName(selectedCountryName);
            countrySearchBox.setText(selectedCountryName);
        }
    }

    /**
     * Event handler for exit button.
     */
    @FXML
    private void onExit() {
        closeApp();
    }

    /**
     * Setter method to assign the CountryController.
     *
     * @param countryController The CountryController.
     */
    public void setCountryController(CountryController countryController) {
        this.countryController = countryController;
    }

    /**
     * Setter method to assign the NewsController.
     *
     * @param newsController The NewsController.
     */
    public void setNewsController(NewsController newsController) {
        this.newsController = newsController;
    }

    /**
     * Setter method to assign the UserDataController.
     *
     * @param userDataController The UserDataController.
     */
    public void setUserDataController(UserDataController userDataController) {
        this.userDataController = userDataController;
    }

    /**
     * Setter method to assign the CountryModel and register the view as an
     * observer.
     *
     * @param countryModel The CountryModel.
     */
    public void setCountryModel(CountryModel countryModel) {
        this.countryModel = countryModel;
        countryModel.addObserver(this);
    }

    /**
     * Setter method to assign the NewsModel and register this view as an
     * observer.
     *
     * @param newsModel The NewsModel.
     */
    public void setNewsModel(NewsModel newsModel) {
        this.newsModel = newsModel;
        newsModel.addObserver(this);
    }

    /**
     * Setter method to assign the UserDataModel and register this view as an
     * observer.
     *
     * @param userDataModel The UserDataModel.
     */
    public void setUserDataModel(UserDataModel userDataModel) {
        this.userDataModel = userDataModel;
        userDataModel.addObserver(this);
    }

    /**
     * Initializes the necessary data for the view after all dependencies
     * (controllers, models) have been set.
     */
    public void initializeData() {
        fetchCountries();
    }

    /**
     * The method that handles different update types by observing the model
     * changes.
     *
     * @param updateType The type of update.
     */
    @Override
    public void update(ObserverUpdateType updateType) {
        switch (updateType) {
            case COUNTRY_LIST_UPDATED ->
                getUserPreference();
            case FILTERED_COUNTRY_LIST_UPDATED ->
                updateFilteredCountryList();
            case COUNTRY_SELECTED_UPDATED ->
                updateCountryViewAndNews();
            case NEWS_LIST_UPDATED ->
                updateNewsList();
            case USER_PREFERENCE_DATA_UPDATED ->
                applyUserPreferences();
            default -> {
            }
        }
    }

    /**
     * Sets up listeners for UI components.
     */
    private void attachListeners() {
        categoryDropdown.getSelectionModel().selectedItemProperty().addListener(categoryListener);
        languageDropdown.getSelectionModel().selectedItemProperty().addListener(languageListener);
        calendarDateFrom.valueProperty().addListener(dateFromListener);
        calendarDateTo.valueProperty().addListener(dateToListener);
    }

    /**
     * Removes listeners from UI components.
     */
    private void detachListeners() {
        categoryDropdown.getSelectionModel().selectedItemProperty().removeListener(categoryListener);
        languageDropdown.getSelectionModel().selectedItemProperty().removeListener(languageListener);
        calendarDateFrom.valueProperty().removeListener(dateFromListener);
        calendarDateTo.valueProperty().removeListener(dateToListener);
    }

    /**
     * Listener for changes to the category drop-down.
     */
    private final ChangeListener<ArticleCategoryEnum> categoryListener = (obs, oldVal, newVal) -> {
        fetchNewsForSelectedCountry();
    };

    /**
     * Listener for changes to the language drop-down.
     */
    private final ChangeListener<ArticleLanguageEnum> languageListener = (obs, oldVal, newVal) -> {
        fetchNewsForSelectedCountry();
    };

    /**
     * Listener for changes to the From date picker.
     */
    private final ChangeListener<LocalDate> dateFromListener = (obs, oldVal, newVal) -> {
        fetchNewsForSelectedCountry();
    };

    /**
     * Listener for changes to the To date picker.
     */
    private final ChangeListener<LocalDate> dateToListener = (obs, oldVal, newVal) -> {
        fetchNewsForSelectedCountry();
    };

    /**
     * Fetches all the countries.
     */
    private void fetchCountries() {
        Task<Void> fetchCountriesTask = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    countryController.fetchAllCountries();
                } catch (Exception ex) {
                    showErrorPopup("Failed to fetch the country list. Please try again later.");
                }
                return null;
            }

            @Override
            protected void scheduled() {
                setLoadingIndicator(true);
            }

            @Override
            protected void succeeded() {
                setLoadingIndicator(false);
            }

            @Override
            protected void failed() {
                setLoadingIndicator(false);
            }
        };
        new Thread(fetchCountriesTask).start();
    }

    /**
     * Update the filtered country list.
     */
    private void updateFilteredCountryList() {
        Platform.runLater(() -> {
            filteredCountries.setItems(countryModel.getFilteredCountries());
            filteredCountries.setPrefHeight(23 * (countryModel.getFilteredCountries()).size());
            if (countrySearchBox.getText().isBlank()) {
                filteredCountries.setVisible(false);
            } else {
                double searchBoxY = countrySearchBox.localToScene(countrySearchBox.getBoundsInLocal()).getMaxY();
                double searchBoxX = countrySearchBox.localToScene(countrySearchBox.getBoundsInLocal()).getMinX();
                StackPane.setMargin(filteredCountries, new Insets(searchBoxY, 0, 0, searchBoxX));
                filteredCountries.setVisible(true);
                filteredCountries.toFront();
            }
        });
    }

    /**
     * Update the selected country's view and fetch news.
     */
    private void updateCountryViewAndNews() {
        if (!isUpdatingFromPreferences) {
            setLanguageAndFetchNews();
        } else {
            isUpdatingFromPreferences = false;
            fetchNewsForSelectedCountry();
        }
        updateCountryView();
    }

    /**
     * Automatically set the language drop-down to the first available language
     * of the selected country.
     */
    private void setLanguageAndFetchNews() {
        ArticleLanguageEnum language = countryModel.getSelectedCountryLanguage();
        languageDropdown.getSelectionModel().selectedItemProperty().removeListener(languageListener);
        Platform.runLater(() -> {
            updateDropdownValue(languageDropdown, language.name(), ArticleLanguageEnum::valueOf, ArticleLanguageEnum.ENGLISH);
            languageDropdown.getSelectionModel().selectedItemProperty().addListener(languageListener);
            fetchNewsForSelectedCountry();
        });
    }

    /**
     * Update country details and fetch news related to the selected country.
     */
    private void fetchNewsForSelectedCountry() {
        String countryName = Util.getCountryName(countryModel);

        if (Objects.isNull(countryName) || countryName.equals("Unknown Country")) {
            showErrorPopup("No country selected");
            return;
        }

        LocalDate dateTo = calendarDateTo.getValue();
        LocalDate dateFrom = calendarDateFrom.getValue();
        if (dateTo != null) {
            if (dateTo.isAfter(LocalDate.now())) {
                showErrorPopup("The To date cannot be a future date. Please select a valid date.");
                return;
            }
            if (dateFrom != null && dateTo.isBefore(dateFrom)) {
                showErrorPopup("The To date cannot be before the From date. Please select a valid date range.");
                return;
            }
        }

        fetchCountryNews();
    }

    /**
     * Fetches all the news articles related to a country.
     */
    private void fetchCountryNews() {
        Task<Void> fetchNewsTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    newsController.fetchTopNewsForCountry(Util.getLanguageCode(languageDropdown),
                            Util.getCountryCode(countryModel),
                            Util.getCategory(categoryDropdown).toLowerCase(),
                            Util.getFromDate(calendarDateFrom),
                            Util.getToDate(calendarDateTo));

                    // Save preferences
                    saveUserPreference();
                } catch (Exception ex) {
                    showErrorPopup("Failed to fetch the news list. Please try again later.");
                }
                return null;
            }

            @Override
            protected void scheduled() {
                setLoadingIndicator(true);
            }

            @Override
            protected void succeeded() {
                setLoadingIndicator(false);
            }

            @Override
            protected void failed() {
                setLoadingIndicator(false);
            }

        };
        new Thread(fetchNewsTask).start();
    }

    /**
     * Update the news list.
     */
    private void updateNewsList() {
        var allNewsList = newsModel.getNewsArticles();

        if (allNewsList != null && !allNewsList.isEmpty()) {
            updateNewsView();
        } else {
            showErrorPopup("No news available.");
        }
    }

    /**
     * Updates the country info UI section according to selected country.
     */
    private void updateCountryView() {
        Country selectedCountry = countryModel.getSelectedCountry();
        CountryView countryView = new CountryView(selectedCountry);

        Platform.runLater(() -> {
            countryInfo.getChildren().clear();
            countryInfo.getChildren().addAll(countryView.createCountryComponent());
            countryView.updateMap(mapView);
        });
    }

    /**
     * Updates the news articles UI according to selected country.
     */
    private void updateNewsView() {
        var newsView = new NewsView();
        var countryName = Util.getCountryName(countryModel);
        var newNewsView = newsView.createArticlesView(this.newsModel.getNewsArticles(), countryName);
        Platform.runLater(() -> {
            newsList.getChildren().clear();
            newsList.getChildren().add(newNewsView);
        });
    }

    /**
     * Save latest user preference based on the selected values.
     */
    private void saveUserPreference() {
        try {
            UserData userData = new UserData(
                    Util.getCountryName(countryModel),
                    Util.getCategory(categoryDropdown).toLowerCase(),
                    Util.getLanguage(languageDropdown),
                    Util.getFromDate(calendarDateFrom),
                    Util.getToDate(calendarDateTo));

            userDataController.saveUserPreference(userData);
        } catch (Exception ex) {
            showErrorPopup("Failed to save the user preference. Please try again later.");
        }
    }

    /**
     * Fetch last saved user preference.
     */
    private void getUserPreference() {
        try {
            userDataController.getSavedUserPreference();
        } catch (Exception ex) {
            showErrorPopup("Failed to fetch the user preference. Please try again later.");
        }
    }

    /**
     * Update the view on application start based on the saved user preference
     * data.
     */
    private void applyUserPreferences() {
        UserData userData = userDataModel.getSavedUserData();
        if (userData != null) {
            detachListeners();
            countryController.filterCountriesByName(userData.getCountry());
            Platform.runLater(() -> {
                updateDropdownValue(languageDropdown, userData.getLanguage(), ArticleLanguageEnum::valueOf, countryModel.getSelectedCountryLanguage());
                updateDropdownValue(categoryDropdown, userData.getCategory(), ArticleCategoryEnum::valueOf, ArticleCategoryEnum.GENERAL);
                updateDatePicker(calendarDateFrom, userData.getFromDate(), LocalDate.now().minusWeeks(1));
                updateDatePicker(calendarDateTo, userData.getToDate(), LocalDate.now());
                updateCountrySelection(userData.getCountry());
                attachListeners();
            });
        } else {
            System.out.println("No saved user data found. Using default values.");
        }
    }

    /**
     * Sets the country search box with saved data.
     *
     * @param countryName Name of the country to set.
     */
    private void updateCountrySelection(String countryName) {
        try {
            countrySearchBox.setText(countryName);
            if (filteredCountries.getItems().contains(countryName)) {
                isUpdatingFromPreferences = true;
                filteredCountries.getSelectionModel().select(countryName);
                countryController.getCountryByName(countryName);
            }
        } catch (NullPointerException e) {
            countrySearchBox.setText("");
        }
    }

    /**
     * Updates a drop-down with a saved value or default value.
     *
     * @param dropdown The drop-down to update.
     * @param value The string value to be set.
     * @param valueOfFunction A function to convert the string to the
     * appropriate enum type.
     * @param defaultValue The default enum value.
     * @param <T> The type of the enum for the drop-down values.
     */
    private <T extends Enum<T>> void updateDropdownValue(ComboBox<T> dropdown, String value, Function<String, T> valueOfFunction, T defaultValue) {
        try {
            dropdown.setValue(valueOfFunction.apply(value));
        } catch (IllegalArgumentException | NullPointerException e) {
            dropdown.getSelectionModel().select(defaultValue);
        }
    }

    /**
     * Updates a DatePicker with a saved date value or default date.
     *
     * @param datePicker The DatePicker to update.
     * @param date The string date to set.
     * @param defaultDate The default LocalDate.
     */
    private void updateDatePicker(DatePicker datePicker, String date, LocalDate defaultDate) {
        try {
            datePicker.setValue(LocalDate.parse(date));
        } catch (DateTimeParseException | NullPointerException e) {
            datePicker.setValue(defaultDate);
        }
    }

    /**
     * Set loading indicator.
     *
     * @param isVisibile Required visibility for the loading indicator.
     */
    private void setLoadingIndicator(boolean isVisibile) {
        Platform.runLater(() -> loadingIndicator.setVisible(isVisibile));
    }

    /**
     * Show error popup.
     *
     * @param message Error message
     */
    private void showErrorPopup(String message) {
        System.err.println(message);
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An Error Occurred");
            alert.setContentText(message);
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        });
    }

    /**
     * Unregister observers from all models to prevent further updates.
     */
    private void unregisterObservers() {
        if (countryModel != null) {
            countryModel.removeObserver(this);
        }
        if (newsModel != null) {
            newsModel.removeObserver(this);
        }
        if (userDataModel != null) {
            userDataModel.removeObserver(this);
        }
    }

    /**
     * Close app and unregister observers.
     */
    private void closeApp() {
        unregisterObservers();
        Platform.exit();
    }

}
