/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.datamodels;

/**
 * Represents a name with both common and official forms.
 * 
 * This class is designed to store and provide access to the common and 
 * official names of an entity, such as a country, organization, or other 
 * named entity.
 * 
 * Fields include:
 * - Common name: the widely used or informal name.
 * - Official name: the formal or official name.
 * 
 * @author Savidya
 */
public class Name {

     /**
     * The common name of the entity.
     */
    private String common;

    /**
     * The official name of the entity.
     */
    private String official;

    /**
     * Constructs a new {@code Name} with the provided common and official names.
     *
     * @param common the common name of the entity
     * @param official the official name of the entity
     */
    public Name(String common, String official) {
        this.common = common;
        this.official = official;
    }

    /**
     * Retrieves the common name.
     *
     * @return the common name as a {@code String}
     */
    public String getCommon() {
        return common;
    }

    /**
     * Retrieves the official name.
     *
     * @return the official name as a {@code String}
     */
    public String getOfficial() {
        return official;
    }
    
}
