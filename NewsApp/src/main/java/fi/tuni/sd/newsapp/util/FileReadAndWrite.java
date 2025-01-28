/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class that provides file read and write methods.
 *
 * This class includes methods for reading from and writing to files, 
 * as well as converting objects to and from their JSON representations 
 * using the Gson library. It is designed to handle basic file operations 
 * such as reading content from a file and writing content to a file.
 * 
 * @author Savidya
 */
public class FileReadAndWrite implements IFileReadAndWrite {

    // Configure Gson to include null fields in JSON output and use pretty printing for readability.
    private final Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    /**
     * Reads the content of a file specified by the file name.
     * 
     * This method reads the content of the file at the given path and returns it as a string.
     * If the file does not exist, a {@link NoSuchFileException} will be thrown.
     * 
     * @param fileName The name or path of the file to read.
     * @return The content of the file as a {@code String}.
     * @throws IOException if the file does not exist or cannot be read.
     */
    @Override
    public String readFromFile(String fileName) throws Exception {
        Path filePath = Paths.get(fileName);

        if (!Files.exists(filePath)) {
            throw new NoSuchFileException("File not found: " + fileName);
        }

        return Files.readString(filePath);
    }

    /**
     * Writes the specified content to a file.
     *
     * @param fileName The name or path of the file to write to.
     * @param content The content to write to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Override
    public void writeToFile(String fileName, String content) throws Exception {
        Path filePath = Paths.get(fileName);

        Files.writeString(filePath, content);
    }

    /**
     * Converts any object to its JSON representation.
     *
     * @param <T> The type of the object to convert.
     * @param object The object to convert
     * @return the JSON representation of the object as a {@code String}
     */
    public <T> String convertObjectToJson(T object) {
        return gson.toJson(object);
    }

    /**
     * Converts a JSON string to a specified object.
     *
     * @param <T> The type of the object to return.
     * @param jsonString The JSON string representing an object.
     * @param objectClass The class of the object to return.
     * @return The object created from the JSON string.
     */
    public <T> T convertJsonToObject(String jsonString, Class<T> objectClass) {
        return gson.fromJson(jsonString, objectClass);
    }

}
