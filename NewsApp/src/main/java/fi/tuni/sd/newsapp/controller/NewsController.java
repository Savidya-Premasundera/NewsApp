/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.controller;

import fi.tuni.sd.newsapp.datamodels.Articles;
import fi.tuni.sd.newsapp.model.NewsModel;
import fi.tuni.sd.newsapp.util.INewsDataProvider;
import static fi.tuni.sd.newsapp.util.service.APIUtil.getFetchAllNewsUrl;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import fi.tuni.sd.newsapp.util.Constant;
import fi.tuni.sd.newsapp.util.service.IHttpConnector;

/**
 * The controller class responsible for managing news-related actions and
 * operations.
 *
 * This class acts as an intermediary between the view and the model for
 * news-related functionality. It provides methods to fetch top news articles
 * for a specific country based on filters like language, category, and date
 * range. It interacts with the News API and updates the {@code NewsModel} with
 * the fetched articles.
 *
 * The controller implements the {@link INewsDataProvider} interface, ensuring
 * that it provides the necessary functionality to interact with the news model
 * and perform data operations.
 *
 * @author Savidya
 */
public class NewsController implements INewsDataProvider {

    /**
     * The model class that holds the news data.
     */
    private final NewsModel newsModel;

    /**
     * The HTTP connector used to make HTTP requests to external services.
     */
    private final IHttpConnector httpConnector;

    /**
     * Constructor that initializes the NewsController with the specified
     * {@code NewsModel}. It also initializes the {@code HttpConnector} to
     * facilitate HTTP requests.
     *
     * @param newsModel The news model class used to store and manage news
     * article data.
     * @param httpConnector The HTTP connector used to make HTTP requests to
     * external APIs.
     */
    public NewsController(NewsModel newsModel, IHttpConnector httpConnector) {
        this.newsModel = newsModel;
        this.httpConnector = httpConnector;
    }

    /**
     * Fetches the top news articles for a given country from the News API.
     *
     * This method retrieves the latest news articles based on the country code,
     * language, category, and date range. It formats the date parameters to
     * ensure proper formatting when making the HTTP request to the News API.
     * The response is then used to update the {@code NewsModel} with the
     * fetched articles.
     *
     * @param language The language code for the news articles.
     * @param countryCode A 2-letter ISO 3166-1 alpha-2 country code.
     * @param NewsCategory The category of news to fetch.
     * @param fromDate The start date for the range of news articles.
     * @param toDate The end date for the range of news articles.
     *
     * @throws Exception if an error occurs during the HTTP request or while
     * processing the response.
     */
    @Override
    public void fetchTopNewsForCountry(
            String language,
            String countryCode,
            String NewsCategory,
            String fromDate,
            String toDate) throws Exception {
        // Get the current time in UTC
        if (toDate.equals(LocalDate.now().toString())) {
            LocalTime currentTime = LocalTime.now().withNano(0);
            toDate = toDate + "T" + currentTime + "Z";
        } else {
            toDate = toDate + Constant.TO_DATE_SUFFIX;
        }

        fromDate = fromDate + Constant.FROM_DATE_SUFFIX;

        var URL = getFetchAllNewsUrl(
                language,
                countryCode.toLowerCase(),
                NewsCategory,
                fromDate,
                toDate);

        // Make the HTTP call with the final dates
        Articles newsResponse = httpConnector.get(URL, Articles.class);

        if (newsResponse != null) {
            newsModel.setNewsArticles(newsResponse.getArticles());
        } else {
            newsModel.setNewsArticles(new ArrayList<>());
        }
    }
    
}
