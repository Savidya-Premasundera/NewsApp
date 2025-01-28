/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.datamodels;

/**
 * Represents an article containing information such as title, description, content,
 * and metadata like publication date and source.
 * 
 * This class provides encapsulated access to article details and supports nested
 * source information through a {@link Source} object.
 * 
 * Fields include:
 * - Title of the article
 * - Description or summary of the article
 * - Full content of the article
 * - URL linking to the article
 * - Image associated with the article
 * - Publication date and time
 * - Source information
 */
public class Article {

    /**
     * The title of the article.
     */
    private String title;

    /**
     * A brief description or summary of the article.
     */
    private String description;

    /**
     * The full content of the article.
     */
    private String content;

    /**
     * The URL linking to the full article.
     */
    private String url;

    /**
     * The URL of the image associated with the article.
     */
    private String image;

    /**
     * The publication date and time of the article in ISO 8601 format.
     */
    private String publishedAt;

    /**
     * The source of the article, represented by a {@link Source} object.
     */
    private Source source;

    /**
     * Retrieves the title of the article.
     * 
     * @return the title of the article.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the description or summary of the article.
     * 
     * @return the description of the article.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the full content of the article.
     * 
     * @return the content of the article.
     */
    public String getContent() {
        return content;
    }

    /**
     * Retrieves the URL linking to the full article.
     * 
     * @return the article's URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Retrieves the URL of the image associated with the article.
     * 
     * @return the URL of the article's image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Retrieves the publication date and time of the article.
     * 
     * @return the publication date in ISO 8601 format.
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * Retrieves the source information of the article.
     * 
     * @return the {@link Source} object containing source details.
     */
    public Source getSource() {
        return source;
    }
    
}

