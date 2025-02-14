/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.datamodels;

/**
 * Represents a flag with its image sources and an alternative text description for accessibility.
 * 
 * The flag includes both PNG and SVG image paths or URLs and an optional alternative
 * text description, which can be used to describe the flag in text-based contexts.
 * 
 * Fields include:
 * - The PNG image file path or URL
 * - The SVG image file path or URL
 * - The alternative text description
 * 
 * @author Savidya
 */
public class Flag {

     /**
     * The path or URL to the PNG image of the flag.
     */
    private String png;

    /**
     * The path or URL to the SVG image of the flag.
     */
    private String svg;

    /**
     * The alternative text description for the flag (used for accessibility).
     */
    private String alt;

    /**
     * Retrieves the PNG image file path or URL.
     *
     * @return the PNG image as a {@code String}
     */
    public String getPng() {
        return png;
    }

    /**
     * Retrieves the SVG image file path or URL.
     *
     * @return the SVG image as a {@code String}
     */
    public String getSvg() {
        return svg;
    }

    /**
     * Retrieves the alternative text description for the image.
     *
     * @return the alternative text as a {@code String}
     */
    public String getAlt() {
        return alt;
    }
    
}
