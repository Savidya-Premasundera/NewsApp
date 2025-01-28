/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.enums;

/**
 * Enumeration to identify the type of observer updates.
 * 
 * This enum defines various update types that can be used to notify observers in 
 * an observer design pattern implementation. It helps in categorizing the events 
 * or updates, enabling observers to act based on the specific update type.
 * 
 * Update types include:
 * - COUNTRY_LIST_UPDATED: Indicates that the list of countries has been updated.
 * - FILTERED_COUNTRY_LIST_UPDATED: Indicates that the filtered country list has been updated.
 * - COUNTRY_SELECTED_UPDATED: Indicates that the selected country information has been updated.
 * - NEWS_LIST_UPDATED: Indicates that the list of news articles has been updated.
 * - USER_PREFERENCE_DATA_UPDATED: Indicates that user preference data has been updated.
 * 
 * @author Savidya
 */
public enum ObserverUpdateType {

    COUNTRY_LIST_UPDATED,
    FILTERED_COUNTRY_LIST_UPDATED,
    COUNTRY_SELECTED_UPDATED,
    NEWS_LIST_UPDATED,
    USER_PREFERENCE_DATA_UPDATED
    
}
