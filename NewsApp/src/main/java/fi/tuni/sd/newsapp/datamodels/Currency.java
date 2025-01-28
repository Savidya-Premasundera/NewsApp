/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.datamodels;

/**
 * Data model representing a currency information
 *
 * @author Savidya
 */
public class Currency {

    private String name;
    private String symbol;

    /**
     * Constructor for Currency
     *
     * @param name The name of the currency
     * @param symbol The symbol of the currency
     */
    public Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Retrieves the name.
     *
     * @return the name as a {@code String}
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the symbol.
     *
     * @return the symbol as a {@code String}
     */
    public String getSymbol() {
        return symbol;
    }
    
}
