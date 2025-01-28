/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fi.tuni.sd.newsapp.util;

/**
 * Interface class that define methods to read from a file and write to a file.
 *
 * @author Savidya
 */
public interface IFileReadAndWrite {

    /**
     * Reads JSON content from a given file.
     *
     * @param fileName Name of the file.
     * @return File content as a String.
     * @throws Exception if any error occurs.
     */
    public String readFromFile(String fileName) throws Exception;

    /**
     * Write JSON content into a file.
     *
     * @param fileName Name of the file.
     * @param content String content to write into file.
     * @throws Exception if any error occurs.
     */
    public void writeToFile(String fileName, String content) throws Exception;
    
}
