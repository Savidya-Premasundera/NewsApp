/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.datamodels;

import java.util.List;

/**
 * Represents a collection of articles and metadata about the total number of articles.
 * 
 * This class provides access to a list of {@link Article} objects and the total count
 * of articles available. It serves as a container for managing multiple articles.
 * 
 * Fields include:
 * - Total number of articles
 * - A list of {@link Article} objects
 * 
 * @author Savidya
 */
public class Articles {

    /**
     * The total number of articles available.
     */
    private int totalArticles;

    /**
     * The list of {@link Article} objects.
     */
    private List<Article> articles;

    /**
     * Retrieves the total number of articles available.
     * 
     * @return the total number of articles.
     */
    public int getTotalArticles() {
        return totalArticles;
    }

    /**
     * Retrieves the list of {@link Article} objects.
     * 
     * @return a {@link List} of articles.
     */
    public List<Article> getArticles() {
        return articles;
    }
    
}
