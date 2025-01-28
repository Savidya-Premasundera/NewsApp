/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package fi.tuni.sd.newsapp;

import fi.tuni.sd.newsapp.controller.CountryController;
import fi.tuni.sd.newsapp.controller.NewsController;
import fi.tuni.sd.newsapp.controller.UserDataController;
import fi.tuni.sd.newsapp.model.CountryModel;
import fi.tuni.sd.newsapp.model.NewsModel;
import fi.tuni.sd.newsapp.model.UserDataModel;
import fi.tuni.sd.newsapp.util.service.HttpConnector;
import fi.tuni.sd.newsapp.util.service.IHttpConnector;
import fi.tuni.sd.newsapp.view.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.stage.Screen;

/**
 * Main class of the News Application.
 *
 * This class serves as the entry point for the JavaFX application. It is
 * responsible for setting up the primary stage, initializing the application
 * models, controllers, and views, and configuring the main application window.
 *
 * @author Savidya
 */
public class Main extends Application {

    private static FXMLLoader fxmlLoader;
    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        createScene();

        // Get the MainView (view-controller) from the FXML loader
        MainView mainView = fxmlLoader.getController();

        // Initialize the concrete IHttpConnector
        IHttpConnector httpConnector = new HttpConnector();

        // Initialize models
        var countryModel = new CountryModel();
        var newsModel = new NewsModel();
        var userDataModel = new UserDataModel();

        // Initialize controllers
        var countryController = new CountryController(countryModel, httpConnector);
        var newsController = new NewsController(newsModel, httpConnector);
        var userDataController = new UserDataController(userDataModel);

        // Set controllers to views
        mainView.setCountryController(countryController);
        mainView.setNewsController(newsController);
        mainView.setUserDataController(userDataController);

        // Set models to views
        mainView.setCountryModel(countryModel);
        mainView.setNewsModel(newsModel);
        mainView.setUserDataModel(userDataModel);

        // Initialize data after all dependencies are set 
        mainView.initializeData();

        // Get primary screen bounds
        var primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        // Set the stage size to match the screen's visual bounds
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());

        // Set the minimum size of the window
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);

        Image icon = new Image(getClass().getResourceAsStream("/assets/news_logo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("News App");
        stage.setScene(scene);
        stage.show();
    }

    private static void createScene() throws IOException {
        scene = new Scene(loadFXML("main"));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static final double MIN_WIDTH = 800;  // Minimum width of the window
    private static final double MIN_HEIGHT = 600; // Minimum height of the window
    
}
