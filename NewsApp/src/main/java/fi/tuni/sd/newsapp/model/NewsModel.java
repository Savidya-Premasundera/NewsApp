/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.model;

import fi.tuni.sd.newsapp.datamodels.Article;
import fi.tuni.sd.newsapp.enums.ObserverUpdateType;
import fi.tuni.sd.newsapp.util.Observable;
import java.util.ArrayList;
import java.util.List;

/**
 * Data model for managing news-related information.
 *
 * This class is responsible for storing and managing a list of news articles. It allows adding
 * and retrieving articles, as well as notifying observers whenever the list of news articles is updated.
 * 
 * @author Savidya
 */
public class NewsModel extends Observable {

    private final List<Article> newsArticles;
    
    /**
     * Constructor that initializes the list of news articles.
     */
    public NewsModel() {
        newsArticles = new ArrayList<>();
    }

    /**
     * Retrieves the list of news articles.
     *
     * @return List of news articles.
     */
    public List<Article> getNewsArticles() {
        return newsArticles;
    }

    /**
     * Sets the list of news articles and notifies observers of the update.
     *
     * @param newsArticles The news list.
     */
    public void setNewsArticles(List<Article> newsArticles) {
        this.newsArticles.clear();
        this.newsArticles.addAll(newsArticles);
        notifyObservers(ObserverUpdateType.NEWS_LIST_UPDATED);
    }
    
}
