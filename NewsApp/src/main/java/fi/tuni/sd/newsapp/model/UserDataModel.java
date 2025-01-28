/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.model;

import fi.tuni.sd.newsapp.datamodels.UserData;
import fi.tuni.sd.newsapp.enums.ArticleCategoryEnum;
import fi.tuni.sd.newsapp.enums.ArticleLanguageEnum;
import fi.tuni.sd.newsapp.enums.ObserverUpdateType;
import static fi.tuni.sd.newsapp.util.Constant.USER_PREFS_FILE_PATH;
import fi.tuni.sd.newsapp.util.FileReadAndWrite;
import fi.tuni.sd.newsapp.util.Observable;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;

/**
 * Data model for managing user preference information.
 *
 * This class handles the user preferences related to country, category,
 * language, and date range. It provides methods to save and retrieve user
 * preferences, and notifies observers when the data is updated.
 *
 * @author Savidya
 */
public class UserDataModel extends Observable {

    private UserData savedUserData;

    private final FileReadAndWrite fileService;

    /**
     * Constructor that initializes the UserDataModel with the
     * {@code FileReadAndWrite}.
     */
    public UserDataModel() {
        fileService = new FileReadAndWrite();
    }

    /**
     * @return the savedUserData
     */
    public UserData getSavedUserData() {
        return savedUserData;
    }

    /**
     * @param savedUserData the savedUserData to set
     */
    public void setSavedUserData(UserData savedUserData) {
        this.savedUserData = savedUserData;
    }

    /**
     * Save latest user preference.
     *
     * @param userData User preference data.
     * @throws Exception if an error occurs during file writing or JSON
     * conversion.
     */
    public void saveUserPreference(UserData userData) throws Exception {
        String jsonString = fileService.convertObjectToJson(userData);
        fileService.writeToFile(USER_PREFS_FILE_PATH, jsonString);
    }

    /**
     * Fetch last saved user preference.
     *
     * @throws Exception if an error occurs during file reading or JSON parsing.
     */
    public void getSavedUserPreference() throws Exception {
        UserData userData;
        try {
            String fileContent = fileService.readFromFile(USER_PREFS_FILE_PATH);
            userData = fileService.convertJsonToObject(fileContent, UserData.class);
        } catch (NoSuchFileException e) {
            System.out.println("Preferences file not found. Using default preferences.");
            userData = createDefaultUserData();
        }
        setSavedUserData(userData);
        notifyObservers(ObserverUpdateType.USER_PREFERENCE_DATA_UPDATED);
    }

    /**
     * Creates a default UserData object with predefined default values.
     *
     * @return UserData object with default values.
     */
    private UserData createDefaultUserData() {
        return new UserData(
                "United States",
                ArticleCategoryEnum.GENERAL.name(),
                ArticleLanguageEnum.ENGLISH.name(),
                LocalDate.now().minusWeeks(1).toString(),
                LocalDate.now().toString()
        );
    }

}
