package fi.tuni.sd.newsapp.util;

import fi.tuni.sd.newsapp.datamodels.UserData;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Interface defining the contract for user data management operations.
 *
 * @author Savidya
 */
public interface IUserDataController {

    /**
     * Saves the user's latest preference to the data storage.
     *
     * @param userData An object containing the user's preference data.
     * @throws Exception if there is any issue with saving the data.
     */
    void saveUserPreference(UserData userData) throws Exception;

    /**
     * Retrieves the last saved user preference from the data storage.
     *
     * @throws Exception if there's an error.
     */
    void getSavedUserPreference() throws Exception;
    
}