/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package fi.tuni.sd.newsapp.enums;

/**
 * Enumeration for article language selection.
 * 
 * This enum provides a mapping of supported languages for articles along with their respective language codes.
 * It allows for a standardized way to handle language preferences in applications.
 * 
 * Each language is represented by its name and its corresponding ISO 639-1 code.
 * 
 * 
 * Languages supported include:
 * - ENGLISH ("en")
 * - ARABIC ("ar")
 * - CHINESE ("zh")
 * - DUTCH ("nl")
 * - FRENCH ("fr")
 * - GERMAN ("de")
 * - GREEK ("el")
 * - HEBREW ("he")
 * - HINDI ("hi")
 * - ITALIAN ("it")
 * - JAPANESE ("ja")
 * - MALAYALAN ("ml")
 * - MARATHI ("mr")
 * - NORWEGIAN ("no")
 * - PORTUGUESE ("pt")
 * - ROMANIAN ("ro")
 * - RUSSIAN ("ru")
 * - SPANISH ("es")
 * - SWEDISH ("sv")
 * - TAMIL ("ta")
 * - TELUGU ("te")
 * - UKRAINIAN ("uk")
 * 
 * @author Savidya
 */
public enum ArticleLanguageEnum {

    ENGLISH("en"),
    ARABIC("ar"),
    CHINESE("zh"),
    DUTCH("nl"),
    FRENCH("fr"),
    GERMAN("de"),
    GREEK("el"),
    HEBREW("he"),
    HINDI("hi"),
    ITALIAN("it"),
    JAPANESE("ja"),
    MALAYALAN("ml"),
    MARATHI("mr"),
    NORWEGIAN("no"),
    PORTUGUESE("pt"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    SPANISH("es"),
    SWEDISH("sv"),
    TAMIL("ta"),
    TELUGU("te"),
    UKRAINIAN("uk");

    private final String code;

    /**
     * Constructor for the enum.
     *
     * @param code The language code for the language
     */
    ArticleLanguageEnum(String code) {
        this.code = code;
    }

    /**
     * Retrieves the language code.
     *
     * @return the language code as a string.
     */
    public String getCode() {
        return code;
    }

}
