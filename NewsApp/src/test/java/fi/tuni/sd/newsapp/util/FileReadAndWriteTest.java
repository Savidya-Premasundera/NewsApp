/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.sd.newsapp.util;

import com.google.gson.GsonBuilder;
import fi.tuni.sd.newsapp.datamodels.UserData;
import fi.tuni.sd.newsapp.enums.ArticleCategoryEnum;
import fi.tuni.sd.newsapp.enums.ArticleLanguageEnum;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FileReadAndWrite class.
 *
 * @author Savidya
 */
public class FileReadAndWriteTest {

    private FileReadAndWrite fileReadAndWrite;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        fileReadAndWrite = new FileReadAndWrite();
        tempFile = Files.createTempFile("testFile", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testReadFromFileSuccess() throws Exception {
        String expectedContent = "File content.";
        Files.writeString(tempFile, expectedContent);

        String result = fileReadAndWrite.readFromFile(tempFile.toString());

        assertEquals(expectedContent, result, "File content should match the expected content");
    }

    @Test
    void testReadFromFileFileNotFound() {
        NoSuchFileException exception = assertThrows(NoSuchFileException.class,
                () -> fileReadAndWrite.readFromFile("non_existent_file.txt"));
        assertEquals("File not found: non_existent_file.txt", exception.getMessage());

    }

    @Test
    void testWriteToFileSuccess() throws Exception {
        String contentToWrite = "File content.";

        fileReadAndWrite.writeToFile(tempFile.toString(), contentToWrite);

        String fileContent = Files.readString(tempFile);

        assertEquals(contentToWrite, fileContent, "The content written to the file should match the expected content");
    }

    @Test
    void testConvertObjectToJson() {
        UserData testData = new UserData("United States", ArticleCategoryEnum.GENERAL.name(), ArticleLanguageEnum.ENGLISH.name(), "2024-11-16", "2024-11-23");
        String expectedJson = """
                              {
                                "country": "United States",
                                "category": "GENERAL",
                                "language": "ENGLISH",
                                "fromDate": "2024-11-16",
                                "toDate": "2024-11-23"
                              }""";

        String jsonResult = fileReadAndWrite.convertObjectToJson(testData);

        assertEquals(expectedJson, jsonResult, "JSON output should match the expected format and content");
    }

    @Test
    void testConvertJsonToObject() {
        String jsonInput = """
                              {
                                "country": "United States",
                                "category": "GENERAL",
                                "language": "ENGLISH",
                                "fromDate": "2024-11-16",
                                "toDate": "2024-11-23"
                              }""";
        UserData result = fileReadAndWrite.convertJsonToObject(jsonInput, UserData.class);

        String expectedData = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(result);

        assertEquals(jsonInput.replaceAll("\\s+", ""), expectedData.replaceAll("\\s+", ""),
                "The JSON representation of the object should match the input JSON");
    }

}
