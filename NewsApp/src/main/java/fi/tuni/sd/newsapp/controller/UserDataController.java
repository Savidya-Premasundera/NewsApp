/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.controller;

import fi.tuni.sd.newsapp.datamodels.UserData;
import fi.tuni.sd.newsapp.model.UserDataModel;
import fi.tuni.sd.newsapp.util.IUserDataController;

/**
 * The controller class responsible for managing user data-related actions and operations.
 * 
 * This class handles user preferences such as country, category, language, and date range for fetching
 * news articles. It interacts with the {@code UserDataModel} to save and retrieve user preferences, 
 * enabling the application to remember and apply these preferences across sessions.
 * 
 * The controller implements the {@link IUserDataController} interface, ensuring that it provides the
 * necessary functionality to interact with the user data model and perform data operations.
 * 
 * @author Savidya
 */
public class UserDataController implements IUserDataController {

    
    /**
     * The model class that holds the user preference data.
     */
    private final UserDataModel userDataModel;

    /**
     * Constructor that initializes the UserDataController with the specified {@code UserDataModel}.
     * 
     * @param userDataModel The user data model class used to store and manage user preference data.
     */
    public UserDataController(UserDataModel userDataModel) {
        this.userDataModel = userDataModel;
    }

    /**
     * Save latest user preference.
     *
     * @param userData User preference data.
     * @throws Exception if an error occurs during the request.
     */
    @Override
    public void saveUserPreference(UserData userData) throws Exception {
        userDataModel.saveUserPreference(userData);
    }

    /**
     * Fetch last saved user preference.
     *
     * @throws Exception if an error occurs during the request.
     */
    @Override
    public void getSavedUserPreference() throws Exception {
        userDataModel.getSavedUserPreference();
    }
    
}
