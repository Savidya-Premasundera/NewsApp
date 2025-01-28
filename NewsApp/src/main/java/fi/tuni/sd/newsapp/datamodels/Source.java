/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.datamodels;

/**
 * Represents the source of an article or piece of information.
 * 
 * This class provides details about the source, including its name and URL.
 * 
 * 
 * Fields include:
 * - Name of the source
 * - URL of the source
 * 
 * @author Savidya
 */
public class Source {

     /**
     * The name of the source.
     */
    private String name;

    /**
     * The URL of the source.
     */
    private String url;

    /**
    * Retrieves the name.
    *
    * @return the name as a {@code String}
    */
    public String getName() {
        return name;
    }

    /**
    * Retrieves the URL.
    *
    * @return the URL as a {@code String}
    */
    public String getUrl() {
        return url;
    }
    
}
